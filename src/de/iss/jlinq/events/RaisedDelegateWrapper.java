package de.iss.jlinq.events;

class RaisedDelegateWrapper<S, P> implements EventDelegate<S, P> {

	private final RaisedDelegate wrapped;
	
	public RaisedDelegateWrapper(RaisedDelegate delegate) {
		this.wrapped = delegate;
	}

	@Override
	public void raised(S sender, P parameter) {
		wrapped.raised();
	}


}
