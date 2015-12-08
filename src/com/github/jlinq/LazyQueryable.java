package com.github.jlinq;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;

class LazyQueryable<T> implements Queryable<T> {

	private final Iterable<T> base;

	public LazyQueryable(Iterable<T> iterable) {
		this.base = iterable;
	}

	@SafeVarargs
	public LazyQueryable(T... elements) {
		this(Arrays.asList(elements));
	}

	@Override
	public Iterator<T> iterator() {
		return base.iterator();
	}

	public <R> Queryable<R> select(final Function<? super T, R> func) {
		final Conversion<T, R> conversion = new Conversion<T, R>() {

			@Override
			public R perform(T value) {
				return func.perform(value);
			}

			@Override
			public boolean isIncluded(T input) {
				return true;
			}
		};
		return new LazyQueryable<R>(
				new LazyQueryableIterable<R>(new Creator<Iterator<R>>() {
					@Override
					public Iterator<R> create() {
						return new LazyIterator<T, R>(base.iterator(), conversion);
					}
				}));

	}

	@Override
	public Queryable<T> where(final Function<? super T, Boolean> func) {
		final Conversion<T, T> conversion = new Conversion<T, T>() {

			@Override
			public T perform(T value) {
				return value;
			}

			@Override
			public boolean isIncluded(T input) {
				return func.perform(input);
			}

		};
		return new LazyQueryable<T>(
				new LazyQueryableIterable<T>(new Creator<Iterator<T>>() {
					@Override
					public Iterator<T> create() {
						return new LazyIterator<T, T>(base.iterator(), conversion);
					}
				}));
	}

	@Override
	public QList<T> toList() {
		QList<T> result = new QArrayList<T>();
		Iterator<T> iter = iterator();
		while (iter.hasNext()) {
			result.add(iter.next());
		}
		return result;
	}

	@Override
	public long count() {
		long result = 0;
		Iterator<T> iter = iterator();
		while (iter.hasNext()) {
			iter.next();
			result++;
		}
		return result;
	}

	@Override
	public T first(T defaultValue) {
		Iterator<T> iter = iterator();
		if (!iter.hasNext())
			return defaultValue;
		return iter.next();
	}

	@Override
	public T first() {
		return first(null);

	}

	@Override
	public T last(T defaultValue) {
		T result = defaultValue;
		Iterator<T> iter = iterator();
		while (iter.hasNext())
			result = iter.next();
		return result;
	}

	@Override
	public T last() {
		return last(null);
	}

	@Override
	public Queryable<T> execute() {
		return new LazyQueryable<T>(toList());
	}

	@Override
	public <R> Map<R, Queryable<T>> group(final Function<? super T, R> func) {
		TreeSet<R> keys = new TreeSet<R>();
		Iterator<T> iter = iterator();
		while (iter.hasNext()) {
			keys.add(func.perform(iter.next()));
		}
		Map<R, Queryable<T>> result = new HashMap<R, Queryable<T>>();
		for (R key : keys) {
			final R keyT = key;
			result.put(key, where(new Function<T, Boolean>() {
				@Override
				public Boolean perform(T a) {
					return func.perform(a).equals(keyT);
				}
			}));
		}
		return result;
	}

	@Override
	public T find(Function<? super T, Boolean> func) {
		return where(func).first();
	}

	@Override
	public boolean contains(T value) {
		Iterator<T> iter = iterator();
		while (iter.hasNext()) {
			if (value == null && iter.next() == null)
				return true;
			if (value.equals(iter.next()))
				return true;
		}
		return false;
	}

	@Override
	public Queryable<T> intersect(final Queryable<T> other) {
		if (other == this)
			return this;
		return where(new Function<T, Boolean>() {
			@Override
			public Boolean perform(T v) {
				return other.contains(v);
			}
		});
	}

	@Override
	public void forEach(ParameterizedCallback<? super T> consumer) {
		Iterator<T> iter = iterator();
		while (iter.hasNext()) {
			consumer.accept(iter.next());
		}
	}

	@Override
	public Queryable<T> filter(final Function<? super T, Boolean> func) {
		return where(new Function<T, Boolean>() {
			@Override
			public Boolean perform(T v) {
				return !func.perform(v);
			}
		});
	}

	@Override
	public Queryable<T> combine(final Queryable<T> other) {
		return new LazyQueryable<T>(new Iterable<T>() {

			@Override
			public Iterator<T> iterator() {
				return new CombinedIterator<T>(LazyQueryable.this.iterator(), other.iterator());
			}

		});
	}
	
	@Override
	public Queryable<T> filterDuplicates() {
		return new LazyQueryable<T>(new Iterable<T>(){

			@Override
			public Iterator<T> iterator() {
				return new UnionIterator<T>(LazyQueryable.this.iterator());
			}
			
		});
	}

	@Override
	public Queryable<T> filterDuplicates(final Function<? super T, ?> unifier) {
		return new LazyQueryable<T>(new Iterable<T>(){

			@Override
			public Iterator<T> iterator() {
				return new UnionIterator<T>(LazyQueryable.this.iterator(), unifier);
			}
			
		});
	}

	@Override
	public T get(int index) {
		int i = 0;
		Iterator<T> iterator = iterator();
		T current = null;
		while (iterator.hasNext()) {
			current = iterator.next();
			if (i == index)
				return current;
			i++;
		}
		throw new NoSuchElementException();
	}

	@Override
	public Queryable<T> get(int index, ParameterizedCallback<? super T> consumer) {
		T element = get(index);
		consumer.accept(element);
		return this;
	}

	@Override
	public <R extends Comparable<? super R>> Queryable<T> orderBy(final Function<? super T, R> func) {
		final SortedSet<T> result = new TreeSet<T>(new Comparator<T>() {

			@Override
			public int compare(T o1, T o2) {
				return func.perform(o1).compareTo(func.perform(o2));
			}

		});
		forEach(new ParameterizedCallback<T>() {
			@Override
			public void accept(T e) {
				result.add(e);
			}
		});
		return new LazyQueryable<T>(result);
	}

	@Override
	public <R extends Comparable<? super R>> Queryable<T> orderInverse(final Function<? super T, R> func) {
		final SortedSet<T> result = new TreeSet<T>(new Comparator<T>() {

			@Override
			public int compare(T o1, T o2) {
				return -1 * func.perform(o1).compareTo(func.perform(o2));
			}

		});
		forEach(new ParameterizedCallback<T>() {
			@Override
			public void accept(T e) {
				result.add(e);
			}
		});
		return new LazyQueryable<T>(result);
	}

	@Override
	public boolean all(Function<? super T, Boolean> condition) {
		Iterator<T> it = iterator();
		T element;
		while(it.hasNext()){
			element = it.next();
			if(!condition.perform(element)) return false;
		}
		return true;
	}

	@Override
	public boolean any(Function<? super T, Boolean> condition) {
		Iterator<T> it = iterator();
		T element;
		while(it.hasNext()){
			element = it.next();
			if(condition.perform(element)) return true;
		}
		return false;
	}

	@Override
	public Queryable<T> notNull() {
		return filter(new Function<T, Boolean>() {
			@Override
			public Boolean perform(T e) {
				return e == null;
			}
		});
	}

	@Override
	public Queryable<T> union(Queryable<T> other) {
		return combine(other).filterDuplicates();
	}

	@Override
	public Queryable<T> union(Queryable<T> other, Function<? super T, ?> unifier) {
		return combine(other).filterDuplicates(unifier);
	}

	

}
