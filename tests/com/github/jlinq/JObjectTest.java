package com.github.jlinq;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JObjectTest {

	private boolean executed = false;

	@Test
	public void testSimple() {
		JObject<Integer> i = new JObject<Integer>(0);
		i.access(new Function<Integer, Integer>() {
			@Override
			public Integer perform(Integer v) {
				return v + 1;
			}
		});
		assertEquals((Integer) 1, i.get());
		executed = false;
		i.conditional(new Function<Integer, Boolean>() {
			@Override
			public Boolean perform(Integer v) {
				return v > 0;
			}
		}, new ParameterizedCallback<Integer>() {
			@Override
			public void accept(Integer v) {
				executed = true;
			}
		});
		assertTrue(executed);
	}

	@Test
	public void testConditional() {
		JObject<Integer> i = new JObject<Integer>(1);
		executed = false;
		i.conditional(new Function<Integer, Boolean>() {
			@Override
			public Boolean perform(Integer v) {
				return v > 0;
			}
		}, new ParameterizedCallback<Integer>() {
			@Override
			public void accept(Integer v) {
				executed = true;
			}
		});
		assertTrue(executed);
		i.conditional(new Function<Integer, Boolean>() {
			@Override
			public Boolean perform(Integer v) {
				return v == 0;
			}
		}, new ParameterizedCallback<Integer>() {
			@Override
			public void accept(Integer v) {
				executed = true;
			}
		}, new ParameterizedCallback<Integer>() {
			@Override
			public void accept(Integer v) {
				executed = false;
			}
		});
		assertFalse(executed);
	}

}
