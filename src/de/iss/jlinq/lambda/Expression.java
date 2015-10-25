package de.iss.jlinq.lambda;

import java.lang.reflect.Method;

public interface Expression {

	static ParameterExpression parameter(Class<?> cl) {
		return new ParameterExpressionImpl(cl);
	}

	static ConstantExpression constant(Object o) {
		return new ConstantObjectExpression(o);
	}
	
	static ConstantExpression constantReference(String ref){
		return new ConstantReferenceExpression(ref);
	}

	static Expressions<Expression> expressions(Expression... values) {
		return new ExpressionsImpl(values);
	}

	static CallExpression call(Expression target, Method m, Expressions<Expression> parameters) {
		return new CallExpressionImpl(target, m, parameters);
	}
	
	static LambdaExpression lambda(Expressions<ParameterExpression> parameters, Expressions<Expression> body){
		return new LambdaExpressionImpl(parameters, body);
	}
	

}
