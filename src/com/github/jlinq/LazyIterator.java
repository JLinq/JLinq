package com.github.jlinq;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A iterator that applies the given conversion while iterating.
 * @author Marcel Singer
 *
 * @param <I> The type of the underlying iterator.
 * @param <T> The type of this iterator.
 */
public class LazyIterator<I, T> implements Iterator<T> {

	private final Iterator<I> input;
	private final Conversion<I, T> conversion;
	
	private boolean hasBuffered = false;
	private T buffered;
	
	/**
	 * Creates a new instance of {@link LazyIterator}.
	 * @param input The underlying iterator.
	 * @param conversion A conversion to be applied to the underlying iterator.
	 */
	public LazyIterator(Iterator<I> input, Conversion<I, T> conversion) {
		this.conversion = conversion;
		this.input = input;
	}

	@Override
	public boolean hasNext() {
		if(hasBuffered) return true;
		if(!input.hasNext()) return false;
		I baseElement = input.next();
		if(!conversion.isIncluded(baseElement)) return hasNext();
		buffered = conversion.perform(baseElement);
		hasBuffered = true;
		return true;
	}

	@Override
	public T next() {
		if(!hasNext()) throw new NoSuchElementException();
		hasBuffered = false;
		return buffered;
	}
	
	

}
