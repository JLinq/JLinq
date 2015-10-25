package de.iss.jlinq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.iss.jlinq.lambda.Expression;
import de.iss.jlinq.lambda.Expressions;

public class ExpressionsTestCase {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws NoSuchMethodException, SecurityException {
		Expression e = Expression.parameter(String.class);
		System.out.println(e);
		System.out.println(Expression.call(e, String.class.getMethod("length"), Expression.expressions()));
	}

}
