package com.github.jlinq.lambda;

import java.lang.reflect.Method;

public interface Expression {

	public static final String LINE_SEPERATOR = "\r\n";

	static ParameterExpression parameter(Class<?> cl) {
		return new ParameterExpressionImpl(cl);
	}

	static ConstantExpression constant(Object o) {
		return new ConstantObjectExpression(o);
	}

	static ConstantExpression constantReference(String ref) {
		return new ConstantReferenceExpression(ref);
	}

	static Expressions<Expression> expressions(Expression... values) {
		return new ExpressionsImpl<Expression>(values);
	}

	static Expressions<ParameterExpression> parameterExpressions(ParameterExpression... values) {
		return new ExpressionsImpl<>(values);
	}

	static CallExpression call(Expression target, Method m, Expressions<Expression> parameters) {
		return new CallExpressionImpl(target, m, parameters);
	}

	static CallExpression call(Expression target, Method m) {
		return call(target, m, new ExpressionsImpl<Expression>(new Expression[0]));
	}

	static LambdaExpression lambda(Class<?> returnType, Expressions<ParameterExpression> parameters,
			Expressions<Expression> body) {
		return new LambdaExpressionImpl(returnType, parameters, body);
	}
	
	

}
