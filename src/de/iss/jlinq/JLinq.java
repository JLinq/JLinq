package de.iss.jlinq;

public interface JLinq {

	
	static <T> Queryable<T> create(Iterable<T> iterable){
		return new LazyQueryable<>(iterable);
	}
	
	@SuppressWarnings("unchecked")
	static <T> Queryable<T> create(T... elements){
		return new LazyQueryable<>(elements);
	}
	
}
