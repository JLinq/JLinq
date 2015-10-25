package de.iss.jlinq;

public interface Conversion<I, O> extends Function<I, O>{

	boolean isIncluded(I input);
	
}
