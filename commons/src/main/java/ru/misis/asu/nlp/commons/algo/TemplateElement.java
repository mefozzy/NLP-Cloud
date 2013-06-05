package ru.misis.asu.nlp.commons.algo;

public abstract class TemplateElement {
	private Object	value;

	protected TemplateElement(final Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public boolean equals(final Object obj) {
		if ((obj == null) || !(obj instanceof TemplateElement)) {
			return false;
		}
		TemplateElement element = (TemplateElement) obj;
		return getValue().equals(element.getValue());
	}

	@Override
	public int hashCode() {
		return getValue().hashCode();
	}

	@Override
	public String toString() {
		return getValue().toString();
	}
}
