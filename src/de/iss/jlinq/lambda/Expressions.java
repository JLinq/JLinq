package de.iss.jlinq.lambda;

public interface Expressions<T extends Expression> extends Expression, ExtendedExpressions<T>{

	public Iterable<T> getExpressions();
	public Expressions<T> append(T expression);
	
}
