package de.iss.jlinq.events;

public interface SenderDelegate<S> {
	
	void raised(S sender);

}
