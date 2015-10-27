package com.github.jlinq.events;

import java.util.HashSet;
import java.util.Set;

public class Event<S, P> {

	private Set<EventDelegate<? super S, ? super P>> listeners = new HashSet<>();

	@SafeVarargs
	public Event(Delegate<S, P>... sources) {
		for (Delegate<S, P> d : sources)
			d.register(this);
	}

	void raise(S sender, P parameter) {
		for (EventDelegate<? super S, ? super P> d : listeners) {
			if (d != null)
				d.raised(sender, parameter);
		}
	}

	public void register(EventDelegate<? super S, ? super P> listener) {
		if (listener == null)
			return;
		listeners.add(listener);
	}
	
	public EventDelegate<S, P> registerParameter(ParameterDelegate<? super P> listener){
		if(listener == null) return null;
		ParameterDelegateWrapper<S,P> wrapper = new ParameterDelegateWrapper<>(listener);
		register(wrapper);
		return wrapper;
	}
	
	public EventDelegate<S, P> registerSender(SenderDelegate<? super S> listener){
		if(listener == null) return null;
		SenderDelegateWrapper<S, P> wrapper = new SenderDelegateWrapper<>(listener);
		register(wrapper);
		return wrapper;
	}
	
	public EventDelegate<S, P> register(RaisedDelegate listener){
		if(listener == null) return null;
		RaisedDelegateWrapper<S, P> wrapper = new RaisedDelegateWrapper<>(listener);
		register(wrapper);
		return wrapper;
	}

	public void remove(EventDelegate<? super S, ? super P> listener) {
		if (listener == null)
			return;
		listeners.remove(listener);
	}

}
