package com.github.jlinq;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class QueryTestCase {

	@Test
	public void testSelect() {
		QList<String> someList = new QArrayList<>();
		someList.add("Hello");
		someList.add("World");
		someList.add("Foo");
		someList.add("Bar");
		Queryable<Character> query = someList.select(v -> v.charAt(0));
		assertEquals(4, query.count());
	}

	@Test
	public void testGet() {
		Queryable<String> q1 = JLinq.create("ab", "cd");
		assertEquals("ab", q1.get(0));
		assertEquals("cd", q1.get(1));

	}

	@Test
	public void testIntersect() {
		Queryable<Integer> q1 = JLinq.create(2, 4, 6, 8, 10, 12);
		Queryable<Integer> q2 = JLinq.create(3, 6, 9, 12, 15);
		Queryable<Integer> result = q1.intersect(q2);
		assertEquals(2, result.count());
		result.get(0, e -> assertEquals((Integer) 6, e)).get(1, e -> assertEquals((Integer) 12, e));
	}

	@Test
	public void testCombine() {
		Queryable<Integer> q1 = JLinq.create(2, 4, 6, 8, 10);
		Queryable<Integer> q2 = JLinq.create(1, 3, 5, 7, 9);
		Queryable<Integer> result = q1.combine(q2);
		assertEquals(10, result.count());
	}

	@Test
	public void testOrderBy() {
		Queryable<String> q1 = JLinq.create("abc", "ab", "a", "abcd", "abcdef", "abcde");
		Queryable<String> result = q1.orderBy(e -> e.length());
		int i = -1;
		for (String e : result) {
			if (e.length() < i)
				fail("The order was invalid.");
			i = e.length();
		}
	}

	@Test
	public void testOrderInverse() {
		Queryable<String> q1 = JLinq.create("abc", "ab", "a", "abcd", "abcdef", "abcde");
		Queryable<String> result = q1.orderInverse(e -> e.length());
		int i = Integer.MAX_VALUE;
		for (String e : result) {
			if (e.length() > i)
				fail("The order was invalid.");
			i = e.length();
		}
	}

	@Test
	public void testWhere() {
		QList<Integer> someList = new QArrayList<>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Queryable<Integer> result = someList.where(v -> v > 5);
		assertTrue(result.count() == 5);
		assertEquals((Integer) 6, result.first());
		assertEquals((Integer) 10, result.last());
	}

	@Test
	public void testUnexecuted() {
		QList<Integer> someList = new QArrayList<>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Queryable<Integer> result = someList.where(v -> v > 5);
		assertEquals(5, result.count());
		someList.add(11);
		assertEquals(6, result.count());
	}

	@Test
	public void testExecuted() {
		QList<Integer> someList = new QArrayList<>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Queryable<Integer> result = someList.where(v -> v > 5).execute();
		assertEquals(5, result.count());
		someList.add(11);
		assertEquals(5, result.count());
	}

}
