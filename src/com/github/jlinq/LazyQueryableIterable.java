package com.github.jlinq;

import java.util.Iterator;


public class LazyQueryableIterable<T> implements Iterable<T>{

	private final Creator<Iterator<T>> iteratorCreator;
	
	public LazyQueryableIterable(Creator<Iterator<T>> iteratorCreator){
		this.iteratorCreator = iteratorCreator;
	}
	
	@Override
	public Iterator<T> iterator() {
		return iteratorCreator.create();
	}

	

}
