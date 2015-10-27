package com.github.jlinq;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


public interface Queryable<T> extends Iterable<T>, JLinq {

	<R> Queryable<R> select(Function<? super T, R> func);
	Queryable<T> where(Function<? super T, Boolean> func);
	<R> Map<R, Queryable<T>> group( Function<? super T, R> func);
	T find(Function<? super T, Boolean> func);
	Queryable<T> filter(Function<? super T, Boolean> func);
	Queryable<T> execute();
	
	List<T> toList();
	long count();
	
	T first(T defaultValue);
	T first();
	
	T last(T defaultValue);
	T last();
	
	boolean contains(T value);
	
	Queryable<T> intersect(Queryable<T> other);
	
	void all(Consumer<? super T> cosumer);
	
	
	default Queryable<T> notNull(){
		return filter(v -> v==null);
	}
	
	
	
}
