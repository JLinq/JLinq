package com.github.jlinq.lambda;

class DeclarationExpressionImpl extends CompiledElement implements DeclarationExpression {

	private final Class<?> type;
	private String name;
	private boolean isAutoName;
	
	public DeclarationExpressionImpl(Class<?> type, String name) {
		this.type = type;
		this.name = name;
		isAutoName = false;
	}
	
	public DeclarationExpressionImpl(Class<?> type){
		this(type, null);
		isAutoName = true;
	}

	@Override
	public String getReference() {
		if(!isInitialized() && name == null) initCompilation(new CompilationContext());
		return name;
	}

	@Override
	protected void doInit(CompilationContext context) {
		if(isAutoName) {
			do{
				name = CodingHelper.getRandomString();
			}while(context.containsLocalVariable(name));
		}
		context.registerLocatVariable(name);
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", type.getName(), getReference());
	}

}
