package de.iss.jlinq.reflection;

import java.lang.reflect.Method;

import de.iss.jlinq.Queryable;

class QClassImpl<T> implements QClass<T> {

	private Class<T> systemClass;
	
	public QClassImpl(Class<T> cl) {
		this.systemClass = cl;
	}

	@Override
	public Class<T> getSystemClass() {
		return systemClass;
	}

	@Override
	public Method method(String name, Class<?>... types) {
		try{
			return systemClass.getMethod(name, types);
		}catch(NoSuchMethodException ex){
			throw new RuntimeException();
		}
	}

}
