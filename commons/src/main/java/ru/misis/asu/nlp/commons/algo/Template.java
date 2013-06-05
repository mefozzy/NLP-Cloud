package ru.misis.asu.nlp.commons.algo;

import java.util.Arrays;

public class Template {
	private TemplateElement[] elements;

	protected Template(final TemplateElement[] elements) {
		if (elements == null) {
			throw new NullPointerException("elements");
		}
		this.elements = elements;
	}

	public TemplateElement[] getElements() {
		return elements;
	}

	public int size() {
		return elements.length;
	}

	@Override
	public boolean equals(final Object obj) {
		if ((obj == null) || !(obj instanceof Template)) {
			return false;
		}
		Template objTemplate = (Template) obj;
		return Arrays.equals(elements, objTemplate.getElements());
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(elements);
	}

	@Override
	public String toString() {
		return Arrays.toString(elements);
	}
}
