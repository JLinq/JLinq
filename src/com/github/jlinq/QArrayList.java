package com.github.jlinq;

import java.util.ArrayList;

/**
 * A {@link Queryable}-list that uses a {@link ArrayList} as data container.
 * @author Marcel Singer
 *
 * @param <T> The type of the elements in this list.
 */
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
