package com.github.jlinq.events;

class SenderDelegateWrapper<S, P> implements EventDelegate<S, P> {

	private final SenderDelegate<? super S> wrapped;
	
	public SenderDelegateWrapper(SenderDelegate<? super S> delegate) {
		this.wrapped = delegate;
	}

	@Override
	public void raised(S sender, P parameter) {
		wrapped.raised(sender);
	}

}
