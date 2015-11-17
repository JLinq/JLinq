package com.github.jlinq;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public interface Queryable<T> extends Iterable<T> {

	/**
	 * Returns a {@link Queryable} that contains the elements selected by the
	 * given function.
	 * 
	 * @param func
	 *            The function to apply.
	 * @return A {@link Queryable} that contains the elements selected by the
	 *         given function.
	 */
	<R> Queryable<R> select(Function<? super T, R> func);

	/**
	 * Returns a {@link Queryable} that contains the elements that fulfill the
	 * given condition.
	 * 
	 * @param func
	 *            The function that tests if an element fulfills a condition.
	 * @return A {@link Queryable} that contains the elements that fulfill the
	 *         given condition.
	 */
	Queryable<T> where(Function<? super T, Boolean> func);

	/**
	 * Groups the elements of this {@link Queryable} using the given function.
	 * <b>Note:</b> This function will usually execute the query by copying the
	 * underlying elements into a new collection. A change to the underlying
	 * collection will therefore (if not specified otherwise) not be applied to
	 * the returned object. See {@link #execute()}.
	 * 
	 * @param func
	 *            The function that returns a value that is used to group the
	 *            elements of this {@link Queryable}.
	 * @return A {@link Map} containing the grouped elements.
	 */
	<R> Map<R, Queryable<T>> group(Function<? super T, R> func);

	/**
	 * Searches for the first element that fulfills the given condition and
	 * returns it.
	 * 
	 * @param func
	 *            The function that tests if an element fulfills a condition.
	 * @return The first matching element.
	 */
	T find(Function<? super T, Boolean> func);

	/**
	 * Removes every element that fulfills the given condition. This is the
	 * inverse of {@link Queryable#select(Function)}.
	 * 
	 * @param func
	 *            The function that tests if an element fulfills a condition.
	 * @return A {@link Queryable} that contains only the elements that did not
	 *         fulfill the given condition.
	 */
	Queryable<T> filter(Function<? super T, Boolean> func);

	/**
	 * Performs all previous operations based on the currently present elements
	 * and returns the result as an independent instance of {@link Queryable}.
	 * Future changes to the underlying collection won't be applied to result of
	 * this method.
	 * 
	 * @return A independent copy of this {@link Queryable}.
	 */
	Queryable<T> execute();

	/**
	 * Orders the elements by comparing the value returned by the given
	 * function. <b>Note:</b> This function will usually execute the query by
	 * copying the underlying elements into a new collection. A change to the
	 * underlying collection will therefore (if not specified otherwise) not be
	 * applied to the returned object. See {@link #execute()}.
	 * 
	 * @param func
	 *            A function that returns the values which should be used for
	 *            the comparison.
	 * @return An ordered {@link Queryable}.
	 */
	<R extends Comparable<? super R>> Queryable<T> orderBy(Function<? super T, R> func);

	/**
	 * Orders the elements by comparing the values returned by the given
	 * function and inverts the order. <b>Note:</b> This function will usually
	 * execute the query by copying the underlying elements into a new
	 * collection. A change to the underlying collection will therefore (if not
	 * specified otherwise) not be applied to the returned object. See
	 * {@link #execute()}.
	 * 
	 * @param func
	 *            A function that returns the values which should be used for
	 *            the comparison.
	 * @return An inverse ordered {@link Queryable}.
	 */
	<R extends Comparable<? super R>> Queryable<T> orderInverse(Function<? super T, R> func);

	/**
	 * Returns a {@link QList} containing all elements of this {@link Queryable}
	 * . <b>Note:</b> This function will usually execute the query by copying
	 * the underlying elements into a new collection. A change to the underlying
	 * collection will therefore (if not specified otherwise) not be applied to
	 * the returned object. See {@link #execute()}.
	 * 
	 * @return A {@link QList} containing all elements of this {@link Queryable}
	 *         .
	 */
	QList<T> toList();

	/**
	 * Returns the amount of elements within this instance.
	 * 
	 * @return The amount of elements within this instance.
	 */
	long count();

	/**
	 * Returns the first element of this query.
	 * 
	 * @param defaultValue
	 *            The value that should be returned if the query contains no
	 *            elements.
	 * @return The first element of this query or the given default value if
	 *         this query is empty.
	 */
	T first(T defaultValue);

	/**
	 * Returns the first element of this query.
	 * 
	 * @return The first element of this query or {@code null} if the query
	 *         contains no elements.
	 */
	T first();

	/**
	 * Returns the last element of this query.
	 * 
	 * @param defaultValue
	 *            The value that should be returned if the query contains no
	 *            elements.
	 * @return The last element of this query or the given default value if this
	 *         query is empty.
	 */
	T last(T defaultValue);

	/**
	 * Returns the last element of this query.
	 * 
	 * @return The last element of this query or {@code null} if the query
	 *         contains no elements.
	 */
	T last();

	/**
	 * Returns {@code true} if this {@link Queryable} contains the given value.
	 * 
	 * @param value
	 *            The value to check.
	 * @return {@code true} if this {@link Queryable} contains the given value.
	 */
	boolean contains(T value);

	/**
	 * Returns all elements that are present in this and the given
	 * {@link Queryable}.
	 * 
	 * @param other
	 *            The other query.
	 * @return All elements that are present in this and the given query.
	 */
	Queryable<T> intersect(Queryable<T> other);

	/**
	 * Returns a query containing the elements of this and the given
	 * {@link Queryable}.
	 * 
	 * @param other
	 *            The orher query.
	 * @return A query containing the elements of this and the given query.
	 */
	Queryable<T> combine(Queryable<T> other);

	/**
	 * Returns the element at the given index.
	 * 
	 * @param index
	 *            The index of the element to return.
	 * @return The element at the given index.
	 * @throws NoSuchElementException
	 *             If there is no element at the given position.
	 */
	T get(int index);

	/**
	 * Passes the element at the given index to the given consumer and returns
	 * this instance.
	 * 
	 * @param index
	 *            The index of the element to be passed to the given consumer.
	 * @param consumer
	 *            The consumer to call.
	 * @return This instance.
	 */
	Queryable<T> get(int index, Consumer<? super T> consumer);

	/**
	 * Iterates over all elements of this query and calls the given consumer.
	 * 
	 * @param cosumer
	 *            The consumer that should be called for all elements of this
	 *            query.
	 */
	void forEach(Consumer<? super T> cosumer);

	/**
	 * Removes all instances of {@code null} from this query.
	 * 
	 * @return A query without {@code null} elements.
	 */
	default Queryable<T> notNull() {
		return filter(v -> v == null);
	}
	
	/**
	 * Returns {@code true} if every element satisfies the given condition.
	 * @param condition The condition to apply.
	 * @return {@code true} if every element satisfies the given condition. 
	 */
	boolean all(Function<? super T, Boolean> condition);

	/**
	 * Returns {@code true} if at least one element satisfies the given condition.
	 * @param condition The condition to apply.
	 * @return {@code true} if at least one element satisfies the given condition.
	 */
	boolean any(Function<? super T, Boolean> condition);
	
}
