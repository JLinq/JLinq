package com.github.jlinq.lambda;

import java.util.ArrayList;
import java.util.List;

public class ExpressionsImpl<T extends Expression> extends CompiledElement implements Expressions<T> {

	private final List<T> expressions = new ArrayList<T>();
	
	
	public ExpressionsImpl(T[] expressions) {
		for(T t : expressions) this.expressions.add(t);
	}

	@Override
	public Iterable<T> getExpressions() {
		return expressions;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for(Expression e : expressions){
			if(!first) sb.append(", "); else first = false;
			sb.append(e.toString());
		}
		return sb.toString();
	}

	@Override
	public Expressions<T> append(T expression) {
		expressions.add(expression);
		return this;
	}

	@Override
	public String getReference() {
		throw new RuntimeException("Not supported!");
	}

	@Override
	protected void doInit(CompilationContext context) {
		for(T exp : expressions) if(exp instanceof CompilationElement) ((CompilationElement) exp).initCompilation(context);
	}

	@Override
	public String getCodeBlock() {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for(Expression e : expressions){
			if(!isFirst) sb.append(LINE_SEPERATOR); else isFirst = false;
			sb.append(String.format("%s;", e));
		}
		return sb.toString();
	}

	@Override
	public String toParameterList(boolean withTypes) {
		StringBuilder sb = new StringBuilder();
		T current;
		for(int i=0; i<expressions.size(); i++){
			if(i > 0) sb.append(", ");
			current = expressions.get(i);
			if(withTypes && (current instanceof CompilationElement)){
				sb.append(((CompilationElement) current).getDeclaration());
			}else sb.append(current);
		}
		return sb.toString();
	}

	@Override
	public String getDeclaration() {
		return getReference();
	}
	

}
