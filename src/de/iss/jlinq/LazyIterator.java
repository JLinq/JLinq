package de.iss.jlinq;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LazyIterator<I, T> implements Iterator<T> {

	private final Iterator<I> input;
	private final Conversion<I, T> conversion;
	
	private boolean hasBuffered = false;
	private T buffered;
	
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
