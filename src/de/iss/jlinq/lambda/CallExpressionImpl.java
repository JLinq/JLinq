package de.iss.jlinq.lambda;

import java.lang.reflect.Method;

class CallExpressionImpl implements CallExpression, CompilationElement {

	private Expression target;
	private Method method;
	private Expressions parameters;

	public CallExpressionImpl(Expression target, Method method, Expressions params) {
		this.target = target;
		this.method = method;
		this.parameters = params;
	}

	@Override
	public void initCompilation(CompilationContext context) {
		if (target instanceof CompilationElement)
			((CompilationElement) target).initCompilation(context);
		if (parameters instanceof CompilationElement)
			((CompilationElement) parameters).initCompilation(context);
	}

	@Override
	public String toString() {
		initCompilation(new CompilationContext());
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
		throw new RuntimeException("Not supported!");

	}

}
