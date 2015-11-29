package com.github.jlinq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JLinqTestCase {


	@Test
	public void testNull() {
		Object a = null;
		int hashCode = JLinq.get(a, new CriticalFunction<Object, Integer>() {
			@Override
			public Integer perform(Object e) throws Throwable {
				return e.hashCode();
			}
		}, -1);
		assertEquals(-1, hashCode);
		String s = null;
		int length = JLinq.get(s, new CriticalFunction<String, Integer>() {
			@Override
			public Integer perform(String e) throws Throwable {
				return e.length();
			}
		}, -42);
		assertEquals(-42, length);
	}
	
	@Test
	public void testException(){
		String a = "Hello World";
		int i = JLinq.get(a, new CriticalFunction<String, Integer>() {
			@Override
			public Integer perform(String e) throws Throwable {
				return (Integer) (Object) e;
			}
		}, -815);
		assertEquals(-815, i);
	}
	
	@Test
	public void testRemoveDuplicates(){
		Queryable<String> strings = JLinq.create("Hello", "World", "Hello", "Universe");
		Queryable<String> cleanedStrings = strings.filterDuplicates();
		assertEquals(3, cleanedStrings.count());
		Queryable<String> strings2 = JLinq.create("ab", "cd", "abc", "cde");
		Queryable<String> cleanedStrings2 = strings2.filterDuplicates(new Function<String, Object>() {
			@Override
			public Object perform(String e) {
				return e.length();
			}
		});
		assertEquals(2, cleanedStrings2.count());
		assertEquals("ab", cleanedStrings2.get(0));
		assertEquals("abc", cleanedStrings2.get(1));
	}
	
	@Test
	public void testValid(){
		String a = "Foo Bar";
		int length = JLinq.get(a, new CriticalFunction<String, Integer>() {
			@Override
			public Integer perform(String e) throws Throwable {
				return e.length();
			}
		}, -10);
		assertEquals(a.length(), length);
	}

}
