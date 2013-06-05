package ru.misis.asu.nlp.commons.algo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class AhoCorasickTest {
	private static class SimpleTemplateElement extends TemplateElement {
		public SimpleTemplateElement(final Character c) {
			super(c);
		}
	}

	private static class SimpleTemplate extends Template {
		public SimpleTemplate(final String string) {
			super(getElementsFromString(string));
		}

		public static TemplateElement[] getElementsFromString(
				final String string) {
			TemplateElement[] elements = new TemplateElement[string.length()];
			for (int i = 0; i < string.length(); i++) {
				elements[i] = new SimpleTemplateElement(string.charAt(i));
			}
			return elements;
		}
	}

	private static TemplateElement[] getAlphabet() {
		String alphabet = "hesiru";
		return SimpleTemplate.getElementsFromString(alphabet);
	}

	private static Template[] getTemplates1() {
		Template[] templates = new Template[4];
		templates[0] = new SimpleTemplate("he");
		templates[1] = new SimpleTemplate("she");
		templates[2] = new SimpleTemplate("his");
		templates[3] = new SimpleTemplate("hers");
		return templates;
	}

	@Test
	public void testAhoCorasick() {
		AhoCorasick automaton = new AhoCorasick(getTemplates1(), getAlphabet());
		Assert.assertNotNull("Strange problem: maybe you're sleeping:"
				+ " AhoCorasick is null after creating", automaton);
	}

	@Test
	public void testGetRoot() {
		AhoCorasick automaton = new AhoCorasick(getTemplates1(), getAlphabet());
		Assert.assertNotNull("No root in AhoCorasick", automaton.getRoot());
	}

	@Test
	public void testGetNext() {
		AhoCorasick automaton = new AhoCorasick(getTemplates1(), getAlphabet());
		String inputString = "usshershehis";
		Map<Template, Integer> output = new HashMap<>();
		AhoCorasick.Node state = automaton.getRoot();
		int curInd = 0;
		while (true) {
			if (curInd >= inputString.length()) {
				break;
			}
			TemplateElement element = new SimpleTemplateElement(
					inputString.charAt(curInd++));
			state = automaton.getNext(state, element);

			Assert.assertNotNull("One of AhoCorasick states is null", state);

			Set<Template> curOut = state.getOutputs();
			for (Template t : curOut) {
				if (output.containsKey(t)) {
					output.put(t, output.get(t) + 1);
				} else {
					output.put(t, 1);
				}
			}
		}

		Map<Template, Integer> validOut = new HashMap<>();
		validOut.put(new SimpleTemplate("he"), 2);
		validOut.put(new SimpleTemplate("she"), 2);
		validOut.put(new SimpleTemplate("hers"), 1);
		validOut.put(new SimpleTemplate("his"), 1);

		for (Template t : validOut.keySet()) {
			Assert.assertEquals("Wrong output from AhoCorasick", output.get(t),
					validOut.get(t));
		}
	}
}
