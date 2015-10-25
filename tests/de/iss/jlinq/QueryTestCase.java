package de.iss.jlinq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QueryTestCase {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		QList<String> someList = new QArrayList<>();
		someList.add("Hello");
		someList.add("World");
		someList.add("Foo");
		someList.add("Bar");
		Queryable<Character> query = someList.select(v -> v.charAt(0));
		assertEquals(4, query.count());
		someList.select(v -> v.charAt(0)).all(v -> System.out.println(v));
	}

}
