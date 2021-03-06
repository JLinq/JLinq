package com.github.jlinq.lambda;

public class ConstantObjectExpression implements ConstantExpression {
	
	private final String  code;

	public ConstantObjectExpression(Object object) {
		if(object == null){
			code = "null";
		}else{
			Class<?> type = object.getClass();
			if(type.isArray()){
				Class<?> arrayType = type.getComponentType();
				if(!isConstantType(arrayType)) throw new IllegalArgumentException("The given object is not a constant expression.");
			}else{
				if(!isConstantType(type)) throw new IllegalArgumentException("The given object is not a constant expression.");
				if(type == String.class){
					code = String.format("\"%s\"", object);
					return;
				}else{
					code = String.format("%s", object);
					return;
				}
			}
		}
		throw new RuntimeException();
	}

	private boolean isConstantType(Class<?> cl){
		return (cl.isPrimitive() || cl == String.class || cl ==Integer.class || cl == Float.class || cl == Double.class || cl == Long.class || cl == Character.class || cl == Byte.class);
	}
	
	@Override
	public String toString() {
		return code;
	}
	
	
}
