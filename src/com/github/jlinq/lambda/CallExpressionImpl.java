package com.github.jlinq.lambda;

import java.lang.reflect.Method;

class CallExpressionImpl extends CompiledElement implements CallExpression {

	private Expression target;
	private Method method;
	private Expressions<Expression> parameters;

	public CallExpressionImpl(Expression target, Method method, Expressions<Expression> params) {
		this.target = target;
		this.method = method;
		this.parameters = params;
	}

	

	@Override
	public String toString() {
		if(!isInitialized()) initCompilation(new CompilationContext());
		String targetString;
		if (target != null) {
			if (target instanceof CompilationElement)
				targetString = ((CompilationElement) target).getReference();
			else
				targetString = target.toString();
			targetString += ".";
		} else {
			targetString = "";
		}
		return String.format("%s%s(%s)", targetString, method.getName(), parameters.toString());
	}

	@Override
	public String getReference() {
		return toString();
	}



	@Override
	protected void doInit(CompilationContext context) {
		if (target instanceof CompilationElement)
			((CompilationElement) target).initCompilation(context);
		if (parameters instanceof CompilationElement)
			((CompilationElement) parameters).initCompilation(context);
	}



	@Override
	public String getDeclaration() {
		return getReference();
	}

}
