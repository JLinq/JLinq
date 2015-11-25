package com.github.jlinq.lambda;

class AssigmentExpressionImpl extends CompiledElement implements AssigmentExpression {

	private final Expression target;
	private final Expression source;

	public AssigmentExpressionImpl(Expression target, Expression source) {
		this.target = target;
		this.source = source;
	}

	@Override
	public String getReference() {
		return toString();
	}

	@Override
	protected void doInit(CompilationContext context) {
		if (target instanceof CompilationElement)
			((CompilationElement) target).initCompilation(context);
		if (source instanceof CompilationElement)
			((CompilationElement) source).initCompilation(context);

	}

	@Override
	public String toString() {
		if (target instanceof DeclarationExpression) {
			if (source instanceof CompilationElement) {
				return String.format("%s = %s", target, ((CompilationElement) source).getReference());
			} else {
				return String.format("%s = %s", target, source);
			}
		} else {
			if (target instanceof CompilationElement) {
				if (source instanceof CompilationElement) {
					return String.format("%s = %s", ((CompilationElement) target).getReference(),
							((CompilationElement) source).getReference());
				} else {
					return String.format("%s = %s", ((CompilationElement) target).getReference(), source);
				}
			} else {
				if (source instanceof CompilationElement) {
					return String.format("%s = %s", target, ((CompilationElement) source).getReference());
				} else {
					return String.format("%s = %s", target, source);
				}
			}
		}
	}

	@Override
	public String getDeclaration() {
		return getReference();
	}
}
