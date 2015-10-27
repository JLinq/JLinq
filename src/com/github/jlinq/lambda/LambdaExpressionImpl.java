package com.github.jlinq.lambda;

import com.github.jlinq.CompilationException;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class LambdaExpressionImpl extends CompiledElement implements LambdaExpression {

	private final Expressions<ParameterExpression> parameters;
	private final Expressions<Expression> body;
	private final Class<?> returnType;

	public LambdaExpressionImpl(Class<?> returnType, Expressions<ParameterExpression> parameters,
			Expressions<Expression> body) {
		this.returnType = returnType;
		this.parameters = parameters;
		this.body = body;
	}

	@Override
	public String toString() {
		initCompilation(new CompilationContext());
		return toLambdaCode();
	}

	private String toLambdaCode() {
		String bodyString = body.getCodeBlock();
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(parameters.toParameterList(false));
		sb.append(") -> ");
		if (bodyString.split(Expression.LINE_SEPERATOR).length == 1) {
			if (bodyString.endsWith(";"))
				bodyString = bodyString.substring(0, bodyString.length() - 1);
			sb.append(bodyString);
		} else {
			sb.append(String.format(") -> {%s", Expression.LINE_SEPERATOR));
			sb.append(CodingHelper.indent(body.getCodeBlock()));
			sb.append(String.format("%s}", Expression.LINE_SEPERATOR));
		}

		return sb.toString();
	}

	private String toMethodCode(String methodName) {
		String bodyString = body.getCodeBlock();
		StringBuilder sb = new StringBuilder();
		sb.append("public ");
		if (returnType == Void.TYPE) {
			sb.append("void");
		} else
			sb.append(returnType.getName());
		sb.append(String.format(" %s(%s) {%s", methodName, parameters.toParameterList(true), Expression.LINE_SEPERATOR));
		sb.append(CodingHelper.indent(bodyString));
		sb.append(String.format("%s}", Expression.LINE_SEPERATOR));
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

	public void compileTo(CtClass c, String methodName) {
		try {
			CompilationContext context = new CompilationContext();
			initCompilation(context);
			CtMethod method;
			String methodCode = toMethodCode(methodName);
			System.out.println(methodCode);
			method = CtNewMethod.make(methodCode, c);
			c.addMethod(method);
		} catch (CannotCompileException e) {
			throw new CompilationException(e);
		}

	}

}
