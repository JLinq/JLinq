package de.iss.jlinq.reflection;

import java.lang.reflect.Method;

import de.iss.jlinq.Queryable;

public interface QClass<T> {
	
	Class<T> getSystemClass();
	
	Method method(String name, Class<?>... types);
	
	
	
	
}
