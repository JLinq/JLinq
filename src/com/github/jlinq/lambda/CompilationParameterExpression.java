package com.github.jlinq.lambda;

interface CompilationParameterExpression extends ParameterExpression, CompilationElement {
	
	public String getName();

	public String getDefinitionCode();
	public String getReferenceCode();
	
}
