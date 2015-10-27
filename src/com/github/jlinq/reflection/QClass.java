package com.github.jlinq.reflection;

import java.lang.reflect.Method;

import com.github.jlinq.Queryable;

public interface QClass<T> {
	
	Class<T> getSystemClass();
	
	Method method(String name, Class<?>... types);
	
	
	
	
}
