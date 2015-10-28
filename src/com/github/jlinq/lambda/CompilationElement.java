package com.github.jlinq.lambda;

interface CompilationElement {
	
	void initCompilation(CompilationContext context);

	String getReference();
	default String getDeclaration(){
		return getReference();
	}
	
}
