package de.iss.jlinq.lambda;

import java.awt.peer.LabelPeer;

public class LambdaExpressionImpl implements LambdaExpression {

	private final Expressions<ParameterExpression> parameters;
	private final Expressions<Expression> body;
	
	public LambdaExpressionImpl(Expressions<ParameterExpression> parameters, Expressions<Expression> body) {
		this.parameters = parameters;
		this.body = body;
	}

	
	private void initCompilation(CompilationContext context) {
		if(parameters instanceof CompilationElement) ((CompilationElement) parameters).initCompilation(context);
		if(body instanceof CompilationElement) ((CompilationElement) body).initCompilation(context);
		
	}

	
	@Override
	public String toString() {
		initCompilation(new CompilationContext());
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		throw new RuntimeException("Not implemented!");
	}
	
	
	

}
