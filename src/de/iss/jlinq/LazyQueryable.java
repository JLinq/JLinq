package de.iss.jlinq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Consumer;

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

	@Override
	public <R> Queryable<R> select(Function<? super T, R> func) {
		Conversion<T, R> conversion = new Conversion<T, R>() {

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
				new LazyQueryableIterable<>(() -> new LazyIterator<T, R>(base.iterator(), conversion)));

	}

	@Override
	public Queryable<T> where(Function<? super T, Boolean> func) {
		Conversion<T, T> conversion = new Conversion<T, T>() {

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
				new LazyQueryableIterable<>(() -> new LazyIterator<T, T>(base.iterator(), conversion)));
	}

	@Override
	public List<T> toList() {
		List<T> result = new ArrayList<>();
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
	public <R> Map<R, Queryable<T>> group(Function<? super T, R> func) {
		TreeSet<R> keys = new TreeSet<>();
		Iterator<T> iter = iterator();
		while (iter.hasNext()) {
			keys.add(func.perform(iter.next()));
		}
		Map<R, Queryable<T>> result = new HashMap<>();
		for (R key : keys) {
			result.put(key, where(a -> func.perform(a).equals(key)));
		}
		return result;
	}

	@Override
	public T find(Function<? super T, Boolean> func) {
		return where(func).first();
	}
	
	@Override
	public boolean contains(T value){
		Iterator<T> iter = iterator();
		while(iter.hasNext()){
			if(value == null && iter.next() == null) return true;
			if(value.equals(iter.next())) return true;
		}
		return false;
	}

	@Override
	public Queryable<T> intersect(Queryable<T> other) {
		if(other == this) return this;
		return where(v -> other.contains(v));
	}
	
	@Override
	public void all(Consumer<? super T> consumer){
		Iterator<T> iter = iterator();
		while(iter.hasNext()){
			consumer.accept(iter.next());
		}
	}

	@Override
	public Queryable<T> filter(Function<? super T, Boolean> func) {
		return where(v -> !func.perform(v));
	}

}
