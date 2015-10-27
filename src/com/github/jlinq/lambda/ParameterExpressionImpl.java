package com.github.jlinq.lambda;

class ParameterExpressionImpl implements CompilationParameterExpression {

	private final Class<?> type;
	
	private String name;
	
	public ParameterExpressionImpl(Class<?> type) {
		this.type = type;
	}

	@Override
	public Class<?> getTypes() {
		return type;
	}

	@Override
	public void initCompilation(CompilationContext context) {
		do{
			name = CodingHelper.getRandomString();
		}while(context.containsLocalVariable(name));
		context.registerParmeter(this);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDefinitionCode() {
		return String.format("%s %s", type.getName(), name);
	}

	@Override
	public String getReferenceCode() {
		return name;
	}
	
	@Override
	public String toString(){
		String nameOld = name;
		name = "param1";
		String result = getDefinitionCode();
		name = nameOld;
		return result;
	}

	@Override
	public String getReference() {
		return name;
	}
	

}
