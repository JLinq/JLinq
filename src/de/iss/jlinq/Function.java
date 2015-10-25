package de.iss.jlinq;

public interface Function<I, O> {

	O perform(I value);
}
