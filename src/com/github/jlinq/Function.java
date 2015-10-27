package com.github.jlinq;

public interface Function<I, O> {

	O perform(I value);
}
