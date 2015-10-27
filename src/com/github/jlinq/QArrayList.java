package com.github.jlinq;

import java.util.ArrayList;

public class QArrayList<T> extends QListWrapper<T> {

	public QArrayList() {
		super(new ArrayList<>());
	}

	@SafeVarargs
	public QArrayList(T... values){
		this();
		for(T v : values)add(v);
	}
	
}
