package com.github.jlinq;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.jlinq.QArrayList;
import com.github.jlinq.QList;
import com.github.jlinq.Queryable;

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
