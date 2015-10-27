package com.github.jlinq.events;

public interface SenderDelegate<S> {
	
	void raised(S sender);

}
