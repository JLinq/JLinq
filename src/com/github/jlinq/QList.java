package com.github.jlinq;

import java.util.List;

public interface QList<T> extends List<T>, Queryable<T> {
	
	/**
	 * Adds the given element to this list.
	 * @param element The element to add.
	 * @return This instance.
	 */
	QList<T> addElement(T element);

	
}
