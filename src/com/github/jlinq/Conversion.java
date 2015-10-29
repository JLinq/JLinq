package com.github.jlinq;

/**
 * Converts a given object.
 * @author Marcel Singer
 *
 * @param <I> The type of the objects to convert.
 * @param <O> The type of the resulting objects.
 */
public interface Conversion<I, O> extends Function<I, O>{

	/**
	 * Tests if the given object should be converted.
	 * @param input The object to test.
	 * @return {@code true} if the given object should be converted.
	 */
	boolean isIncluded(I input);
	
}
