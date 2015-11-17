package com.github.jlinq;

public interface CriticalFunction<I, O>  {

	
	O perform(I input) throws Throwable;
	
	
}
