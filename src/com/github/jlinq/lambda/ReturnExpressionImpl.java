package com.github.jlinq.lambda;

class ReturnExpressionImpl extends CompiledElement implements ReturnExpression {

	private Expression exp;
	
	public ReturnExpressionImpl(Expression returnValue) {
		this.exp = returnValue;
	}

	@Override
	public String getReference() {
		throw new RuntimeException("Not supported!");
	}

	@Override
	protected void doInit(CompilationContext context) {
		if(exp != null && exp instanceof CompilationElement) ((CompilationElement) exp).initCompilation(context);
	}
	
	@Override
	public String toString() {
		if(exp == null) return "return";
		if(exp instanceof CompilationElement) return String.format("return %s", ((CompilationElement) exp).getReference());
		return String.format("return %s", exp);
	}

	@Override
	public String getDeclaration() {
		return getReference();
	}
	
	

}
