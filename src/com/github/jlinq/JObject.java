package com.github.jlinq;

import java.util.function.Consumer;

public class JObject<T> {

	private T object;
	
	public JObject(T object) {
		this.object = object;
	}
	
	public JObject(){
		this(null);
	}
	
	
	public void access(Consumer<? super T> consumer){
		if(consumer != null) consumer.accept(object);
	}
	
	public void access(Function<? super T, T> function){
		if(function != null) object = function.perform(object);
	}
	
	public T get(){
		return object;
	}
	
	public void set(T value){
		object = value;
	}
	
	public boolean conditional(Function<? super T, Boolean> function, Consumer<? super T> then, Consumer<? super T> alternative){
		if(function.perform(object)){
			if(then != null) then.accept(object);
			return true;
		}else{
			if(alternative != null) alternative.accept(object);
			return false;
		}
	}
	
	public boolean conditional(Function<? super T, Boolean> function, Consumer<? super T> then){
		return conditional(function, then, null);
	}
	
	public JObject<T> copy(JObject<T> target){
		target.access(new Function<T, T>() {

			@Override
			public T perform(T value) {
				return object;
			}
		});
		return this;
	}
	
	@Override
	public String toString() {
		return String.format("%s", object);
	}

}
