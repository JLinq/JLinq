package de.iss.jlinq;

import java.util.ArrayList;

public class QArrayList<T> extends QListWrapper<T> {

	public QArrayList() {
		super(new ArrayList<>());
	}

}
