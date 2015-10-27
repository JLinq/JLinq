package de.iss.jlinq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.iss.jlinq.lambda.CallExpression;
import de.iss.jlinq.lambda.Expression;
import de.iss.jlinq.lambda.Expressions;
import de.iss.jlinq.lambda.LambdaExpression;
import de.iss.jlinq.lambda.ParameterExpression;

public class ExpressionsTestCase {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws NoSuchMethodException, SecurityException {
		ParameterExpression e = Expression.parameter(String.class);
		System.out.println(e);
		CallExpression ce = Expression.call(e, String.class.getMethod("length"));
		LambdaExpression le = Expression.lambda(Expression.expressions(e), Expression.expressions(ce));
		System.out.println(le);
	}

}
