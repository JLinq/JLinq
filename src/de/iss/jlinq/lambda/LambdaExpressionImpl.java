package de.iss.jlinq.lambda;

public class LambdaExpressionImpl extends CompiledElement implements LambdaExpression {

	private final Expressions<ParameterExpression> parameters;
	private final Expressions<Expression> body;

	public LambdaExpressionImpl(Expressions<ParameterExpression> parameters, Expressions<Expression> body) {
		this.parameters = parameters;
		this.body = body;
	}

	@Override
	public String toString() {
		initCompilation(new CompilationContext());
		String bodyString = body.getCodeBlock();
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(parameters.toParameterList());
		sb.append(") -> "); 
		if(bodyString.split(Expression.LINE_SEPERATOR).length == 1){
			if(bodyString.endsWith(";")) bodyString = bodyString.substring(0, bodyString.length()-1);
			sb.append(bodyString);
		}else{
			sb.append(String.format(") -> {%s", Expression.LINE_SEPERATOR));
			sb.append(CodingHelper.indent(body.getCodeBlock()));
			sb.append(String.format("%s}", Expression.LINE_SEPERATOR));
		}
	
		return sb.toString();
	}

	@Override
	public String getReference() {
		throw new RuntimeException("Not supported!");
	}

	@Override
	protected void doInit(CompilationContext context) {
		if (parameters instanceof CompilationElement)
			((CompilationElement) parameters).initCompilation(context);
		if (body instanceof CompilationElement)
			((CompilationElement) body).initCompilation(context);
	}

}
