package com.github.jlinq.lambda;

class ParameterExpressionImpl extends CompiledElement implements CompilationParameterExpression {

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
		if(!isInitialized()) initCompilation(new CompilationContext());
		return name;
	}

	@Override
	public String getReference() {
		return name;
	}

	@Override
	protected void doInit(CompilationContext context) {
		do{
			name = CodingHelper.getRandomString();
		}while(context.containsLocalVariable(name));
		context.registerParmeter(this);
	}
	
	@Override
	public String getDeclaration() {
		return String.format("%s %s", type.getName(), name);
	}
	

}
