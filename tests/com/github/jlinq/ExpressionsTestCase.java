package com.github.jlinq;

import static org.junit.Assert.*;

import java.io.PrintStream;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import com.github.jlinq.lambda.CallExpression;
import com.github.jlinq.lambda.LambdaExpression;
import com.github.jlinq.lambda.ParameterExpression;
import com.github.jlinq.lambda.QExpression;

import javassist.ClassPool;
import javassist.CtClass;

public class ExpressionsTestCase {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSimpleGetter() throws Exception {
		ParameterExpression e = QExpression.parameter(String.class);
		CallExpression ce = QExpression.call(e, String.class.getMethod("length"));
		CallExpression syso = QExpression.call(QExpression.constantReference("System.out"), PrintStream.class.getMethod("println", String.class), QExpression.expressions(ce));
		CallExpression syso2 = QExpression.call(QExpression.constantReference("System.out"), PrintStream.class.getMethod("println", String.class), QExpression.expressions(e));
		LambdaExpression le = QExpression.lambda(String.class, QExpression.parameterExpressions(e), QExpression.expressions(syso).append(syso2).append(QExpression.returnExpression(e)));
		System.out.println(le);
		ClassPool cp = new ClassPool(true);
		CtClass c = cp.makeClass("TestClass");
		le.compileTo(c, "testMethod");
		Class<?> systemClass = c.toClass();
		Object smth = systemClass.newInstance();
		Method m = systemClass.getMethod("testMethod", String.class);
		assertNotNull(m);
		String result = (String) m.invoke(smth, "Hello World!");
		assertEquals("Hello World!", result);
	}

}
