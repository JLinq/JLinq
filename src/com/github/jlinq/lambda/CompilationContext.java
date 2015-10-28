package com.github.jlinq.lambda;

import java.util.ArrayList;

import com.github.jlinq.QList;
import com.github.jlinq.QListWrapper;

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
	
	public void registerLocatVariable(String varName){
		localVariables.add(varName);
	}

}
