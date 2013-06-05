package ru.misis.asu.nlp.commons;

import java.util.Iterator;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.cas.FSArray;


public class FSArrayIteratorAdapter implements Iterator<FeatureStructure> {
	private FSArray	array;
	private int		currentElement;

	public FSArrayIteratorAdapter(final FSArray array) {
		this.array = array;
		currentElement = 0;
	}

	@Override
	public boolean hasNext() {
		return currentElement < array.size();
	}

	@Override
	public FeatureStructure next() {
		if (!hasNext()) {
			return null;
		}
		return array.get(currentElement++);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	public Iterator<String> getStringIterator(final FSToStringEncoder encoder) {
		return new Iterator<String>() {
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

			@Override
			public String next() {
				return encoder.getString(FSArrayIteratorAdapter.this.next());
			}

			@Override
			public boolean hasNext() {
				return FSArrayIteratorAdapter.this.hasNext();
			}
		};
	}
}
