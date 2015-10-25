package de.iss.jlinq.lambda;

import java.util.ArrayList;

import de.iss.jlinq.QList;
import de.iss.jlinq.QListWrapper;

class CompilationContext {
	
	private QList<String> localVariables = new QListWrapper<>(new ArrayList<>());

	public CompilationContext() {
		
	}
	
	public boolean containsLocalVariable(String variable){
		return localVariables.contains(variable);
	}
	
	public void registerParmeter(CompilationParameterExpression p){
		localVariables.add(p.getName());
	}

}
