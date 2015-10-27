package com.github.jlinq;

import static org.junit.Assert.*;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.rmi.server.ExportException;

import org.junit.Before;
import org.junit.Test;

import com.github.jlinq.lambda.CallExpression;
import com.github.jlinq.lambda.Expression;
import com.github.jlinq.lambda.Expressions;
import com.github.jlinq.lambda.LambdaExpression;
import com.github.jlinq.lambda.ParameterExpression;

import javassist.ClassPool;
import javassist.CtClass;

public class ExpressionsTestCase {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws Exception {
		ParameterExpression e = Expression.parameter(String.class);
		System.out.println(e);
		CallExpression ce = Expression.call(e, String.class.getMethod("length"));
		CallExpression syso = Expression.call(Expression.constantReference("System.out"), PrintStream.class.getMethod("println", String.class), Expression.expressions(ce));
		CallExpression syso2 = Expression.call(Expression.constantReference("System.out"), PrintStream.class.getMethod("println", String.class), Expression.expressions(e));
		LambdaExpression le = Expression.lambda(Void.TYPE, Expression.parameterExpressions(e), Expression.expressions(syso).append(syso2));
		System.out.println(le);
		ClassPool cp = new ClassPool(true);
		CtClass c = cp.makeClass("TestClass");
		le.compileTo(c, "testMethod");
		Class<?> systemClass = c.toClass();
		Object smth = systemClass.newInstance();
		Method m = systemClass.getMethod("testMethod", String.class);
		assertNotNull(m);
		m.invoke(smth, "Hello World!");
	}

}
