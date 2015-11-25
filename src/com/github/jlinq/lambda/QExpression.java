package com.github.jlinq.lambda;

import java.lang.reflect.Method;

public  class QExpression {
	
	protected QExpression(){
		
	}
	

	public static ParameterExpression parameter(Class<?> cl) {
		return new ParameterExpressionImpl(cl);
	}

	public static ConstantExpression constant(Object o) {
		return new ConstantObjectExpression(o);
	}

	public static ConstantExpression constantReference(String ref) {
		return new ConstantReferenceExpression(ref);
	}

	public static Expressions<Expression> expressions(Expression... values) {
		return new ExpressionsImpl<Expression>(values);
	}

	public static Expressions<ParameterExpression> parameterExpressions(ParameterExpression... values) {
		return new ExpressionsImpl<ParameterExpression>(values);
	}

	public static CallExpression call(Expression target, Method m, Expressions<Expression> parameters) {
		return new CallExpressionImpl(target, m, parameters);
	}

	public static CallExpression call(Expression target, Method m) {
		return call(target, m, new ExpressionsImpl<Expression>(new Expression[0]));
	}

	public static LambdaExpression lambda(Class<?> returnType, Expressions<ParameterExpression> parameters,
			Expressions<Expression> body) {
		return new LambdaExpressionImpl(returnType, parameters, body);
	}
	
	public static ReturnExpression returnExpression(Expression returnValue){
		return new ReturnExpressionImpl(returnValue);
	}
	
	public static ReturnExpression returnExpression(){
		return returnExpression(null);
	}
	
	public static DeclarationExpression declaration(Class<?> type, String name){
		return new DeclarationExpressionImpl(type, name);
	}
	
	public static DeclarationExpression declaration(Class<?> type){
		return new DeclarationExpressionImpl(type);
	}
	
	public static AssigmentExpression assign(Expression target, Expression source){
		return new AssigmentExpressionImpl(target, source);
	}

}
