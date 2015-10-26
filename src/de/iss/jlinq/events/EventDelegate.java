package de.iss.jlinq.events;

public interface EventDelegate<S, P> {
	
	void raised(S sender, P parameter);

}
