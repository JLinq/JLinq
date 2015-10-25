package de.iss.jlinq.lambda;

class ConstantReferenceExpression implements ConstantExpression {

	private final String referenced;
	
	public ConstantReferenceExpression(String referenced) {
		this.referenced = referenced;
	}

	

}
