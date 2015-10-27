package com.github.jlinq.lambda;

import javassist.CtClass;

public interface LambdaExpression {


	
	void compileTo(CtClass c, String methodName);

}
