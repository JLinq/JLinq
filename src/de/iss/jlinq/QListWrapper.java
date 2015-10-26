package de.iss.jlinq;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class QListWrapper<T> extends LazyQueryable<T> implements QList<T> {

	private final List<T> base;
	
	public QListWrapper(List<T> base) {
		super(base);
		this.base = base;
	}

	@Override
	public int size() {
		return base.size();
	}

	@Override
	public boolean isEmpty() {
		return base.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return base.contains(o);
	}

	@Override
	public Object[] toArray() {
		return base.toArray();
	}

	@Override
	public <V> V[] toArray(V[] a) {
		return base.toArray(a);
	}

	@Override
	public boolean add(T e) {
		return base.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return base.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return base.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return base.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		return base.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return base.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return base.retainAll(c);
	}

	@Override
	public void clear() {
		base.clear();
	}

	@Override
	public T get(int index) {
		return base.get(index);
	}

	@Override
	public T set(int index, T element) {
		return base.set(index, element);
	}

	@Override
	public void add(int index, T element) {
		base.add(index, element);
	}

	@Override
	public T remove(int index) {
		return base.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return base.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return base.lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator() {
		return base.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return base.listIterator(index);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return base.subList(fromIndex, toIndex);
	}
	

}
