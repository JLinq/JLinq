package com.github.jlinq.events;

class ParameterDelegateWrapper<S, P> implements EventDelegate<S, P> {

	private ParameterDelegate<? super P> wrapped;
	
	public ParameterDelegateWrapper(ParameterDelegate<? super P> delegate) {
		this.wrapped = delegate;
	}

	@Override
	public void raised(S sender, P parameter) {
		wrapped.raised(parameter);
	}
	
	

}
