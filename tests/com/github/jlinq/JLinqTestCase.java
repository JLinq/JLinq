package com.github.jlinq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JLinqTestCase {


	@Test
	public void testNull() {
		Object a = null;
		int hashCode = JLinq.get(a, e -> e.hashCode(), -1);
		assertEquals(-1, hashCode);
		String s = null;
		int length = JLinq.get(s, e -> e.length(), -42);
		assertEquals(-42, length);
	}
	
	@Test
	public void testException(){
		String a = "Hello World";
		int i = JLinq.get(a, e -> (Integer) (Object) e, -815);
		assertEquals(-815, i);
	}
	
	@Test
	public void testValid(){
		String a = "Foo Bar";
		int length = JLinq.get(a, e -> e.length(), -10);
		assertEquals(a.length(), length);
	}

}
