package com.github.jlinq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JObjectTest {

	private boolean executed = false;
	
	@Test
	public void testSimple() {
		JObject<Integer> i = new JObject<>(0);
		i.access(v -> v + 1);
		assertEquals((Integer) 1, i.get());
		executed = false;
		i.conditional(v -> v > 0, v -> executed = true);
		assertTrue(executed);
	}
	
	@Test
	public void testConditional(){
		JObject<Integer> i = new JObject<>(1);
		executed = false;
		i.conditional(v -> v > 0, v -> executed = true);
		assertTrue(executed);
		i.conditional(v -> v == 0, v -> executed = true, v -> executed = false);
		assertFalse(executed);
	}
	
	

}
