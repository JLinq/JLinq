package com.github.jlinq;

/**
 * A simple object factory.
 * @author Marcel Singer
 *
 * @param <T> The type of the object(s) that will be created by this creator.
 */
public interface Creator<T> {

	/**
	 * Creates an object.
	 * @return The created object.
	 */
	T create();
	
}
