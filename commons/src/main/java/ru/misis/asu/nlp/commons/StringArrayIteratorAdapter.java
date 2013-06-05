package ru.misis.asu.nlp.commons;

import java.util.Iterator;

import org.apache.uima.jcas.cas.StringArray;


public class StringArrayIteratorAdapter implements Iterator<String> {
	private StringArray	array;
	private int			currentElement;

	public StringArrayIteratorAdapter(final StringArray array) {
		this.array = array;
		currentElement = 0;
	}

	@Override
	public boolean hasNext() {
		return currentElement < array.size();
	}

	@Override
	public String next() {
		if (!hasNext()) {
			return null;
		}
		return array.get(currentElement++);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
