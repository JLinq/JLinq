package com.github.jlinq.lambda;

class ConstantReferenceExpression extends CompiledElement implements ConstantExpression {

	private final String referenced;
	
	public ConstantReferenceExpression(String referenced) {
		this.referenced = referenced;
	}

	@Override
	public String getReference() {
		return referenced;
	}

	@Override
	protected void doInit(CompilationContext context) {
		
	}

	@Override
	public String getDeclaration() {
		return getReference();
	}

	

}
