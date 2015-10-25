package de.iss.jlinq;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CombinedIterator<T> implements Iterator<T> {
	
	private final Iterator<T> iterator1;
	private final Iterator<T> iterator2;

	public CombinedIterator(Iterator<T> i1, Iterator<T> i2) {
		this.iterator1 = i1;
		this.iterator2 = i2;
	}

	@Override
	public boolean hasNext() {
		if(iterator1.hasNext()) return true;
		return iterator2.hasNext();
	}

	@Override
	public T next() {
		if(iterator1.hasNext()) return iterator1.next();
		if(iterator2.hasNext()) return iterator2.next();
		throw new NoSuchElementException();
	}
	
	

}
