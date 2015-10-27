package com.github.jlinq.events;

import java.util.HashSet;
import java.util.Set;

public class Delegate<S, P> {

	private Set<Event<S, P>> events = new HashSet<>();

	public Delegate() {

	}

	public void raise(S sender, P parameter) {
		for (Event<S, P> event : events) {
			if (event != null)
				event.raise(sender, parameter);
		}
	}

	void register(Event<S, P> event) {
		events.add(event);
	}

}
