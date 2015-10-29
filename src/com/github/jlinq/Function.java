package com.github.jlinq;

/**
 * A function that takes one input an argument.
 * @author Marcel Singer
 *
 * @param <I> The type of the accepted input argument.
 * @param <O> The type of the result.
 */
public interface Function<I, O> {

	O perform(I value);
}
