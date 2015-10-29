package com.github.jlinq;

/**
 * Provides helper-methods to convert or wrap standard collections.
 * @author Marcel Singer
 *
 */
public class  JLinq {

	protected JLinq(){
		
	}
	
	/**
	 * Returns an implementation of {@link Queryable} that wraps the given {@link Iterable}.
	 * @param iterable The {@link Iterable} to be wrapped.
	 * @return An implementation of {@link Queryable} that wraps the given {@link Iterable}.
	 */
	public static <T> Queryable<T> create(Iterable<T> iterable){
		return new LazyQueryable<>(iterable);
	}
	/**
	 * Creates an implementation of {@link Queryable} by inserting the given elements into a list.
	 * @param elements The elements to be included.
	 * @return An implementation of {@link Queryable} that contains the given elements.
	 */
	@SuppressWarnings("unchecked")
	public static <T> Queryable<T> create(T... elements){
		return new LazyQueryable<>(elements);
	}
	
}
