package com.github.jlinq;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnionIterator<T> implements Iterator<T> {

	private QList<Object> containing = new QArrayList<Object>();
	private final Function<? super T, ?> unifier;
	private Iterator<T> basicIterator;
	private T next;
	
	public UnionIterator(Iterator<T> basicIterator, Function<? super T, ?> unifier) {
		this.basicIterator = basicIterator;
		this.unifier = unifier;
	}
	
	public UnionIterator(Iterator<T> basicIterator){
		this(basicIterator, new Function<T, Object>() {
			@Override
			public Object perform(T e) {
				return e;
			}
		});
	}

	@Override
	public boolean hasNext() {
		if(next != null) return true;
		T temp;
		Object tempUnifier;
		while(basicIterator.hasNext()){
			temp = basicIterator.next();
			tempUnifier = unifier.perform(temp);
			if(!containing.contains(tempUnifier)){
				containing.add(tempUnifier);
				next = temp;
				return true;
			}
		}
		return false;
	}

	@Override
	public T next() {
		if(!hasNext()) throw new NoSuchElementException();
		T result = next;
		next = null;
		return result;
	}

}
