package de.iss.jlinq.lambda;

interface CompilationElement {
	
	void initCompilation(CompilationContext context);

	String getReference();
	
}
