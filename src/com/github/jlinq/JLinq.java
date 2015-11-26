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
		return new LazyQueryable<T>(iterable);
	}
	/**
	 * Creates an implementation of {@link Queryable} by inserting the given elements into a list.
	 * @param elements The elements to be included.
	 * @return An implementation of {@link Queryable} that contains the given elements.
	 */
	public static <T> Queryable<T> create(T... elements){
		return new LazyQueryable<T>(elements);
	}
	
	/**
	 * Performs an operation on a given object and returns the result.
	 * @param object The object to process.
	 * @param selector The function that performs the operation.
	 * @param defaltValue The value that is returned if the given function throws an exception.
	 * @return The value returned by the given function or the specified default value.
	 */
	public static <T, V> V get(T object, CriticalFunction<T, V> selector, V defaltValue){
		if(object == null) return defaltValue;
		try{
			V result = selector.perform(object);
			return result;
		}catch(Throwable th){
			return defaltValue;
		}
	}
	
	/**
	 * Performs an operation on a given object and returns the result or the default value if the function returns {@code null} or fails with an exception.
	 * @param object The object to process.
	 * @param selector The function that performs the operation.
	 * @param defaultValue The value that is returned if the given function throws an exception or returns {@code null}.
	 * @return The value returned by the given function or the specified default value.
	 */
	public static <T, V> V getNotNull(T object, CriticalFunction<T,V> selector, V defaultValue){
		V result = get(object, selector, defaultValue);
		if(result == null) result = defaultValue;
		return result;
	}
	
	
	
}
