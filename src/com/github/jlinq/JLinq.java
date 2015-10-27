package com.github.jlinq;

public class  JLinq {

	protected JLinq(){
		
	}
	
	public static <T> Queryable<T> create(Iterable<T> iterable){
		return new LazyQueryable<>(iterable);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Queryable<T> create(T... elements){
		return new LazyQueryable<>(elements);
	}
	
}
