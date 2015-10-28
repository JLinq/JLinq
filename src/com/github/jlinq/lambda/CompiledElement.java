package com.github.jlinq.lambda;

abstract class CompiledElement implements CompilationElement{

	private CompilationContext lastContext;
	
	
	@Override
	public void initCompilation(CompilationContext context) {
		if(lastContext != context) doInit(context);
		lastContext = context;
	}
	
	protected abstract void doInit(CompilationContext context);
	
	protected boolean isInitialized(){ return lastContext != null; }
	
}
