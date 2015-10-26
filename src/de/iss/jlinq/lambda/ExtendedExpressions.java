package de.iss.jlinq.lambda;

public interface ExtendedExpressions<T extends Expression> {

	public String getCodeBlock();
	public String toParameterList();
}
