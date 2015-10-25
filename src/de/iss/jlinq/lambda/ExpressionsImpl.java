package de.iss.jlinq.lambda;

public class ExpressionsImpl<T extends Expression> implements Expressions<T> {

	private final T[] expressions;
	
	public ExpressionsImpl(T[] expressions) {
		this.expressions = expressions;
	}

	@Override
	public T[] getExpressions() {
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
	

}
