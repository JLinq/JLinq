package com.github.jlinq.events;

public interface EventDelegate<S, P> {
	
	void raised(S sender, P parameter);

}
