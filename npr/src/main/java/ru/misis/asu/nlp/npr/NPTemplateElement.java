package ru.misis.asu.nlp.npr;

import java.util.BitSet;

import ru.misis.asu.nlp.commons.algo.TemplateElement;
import ru.misis.asu.nlp.morphoanalysis.model.Wordform;
import ru.misis.asu.nlp.morphoanalysis.model.Wordform.Builder;
import ru.misis.asu.nlp.morphoanalysis.resource.MorphDictionary;

public class NPTemplateElement extends TemplateElement {
	public NPTemplateElement(final MorphDictionary dict, final String[] grammems) {
		super(getBitSetFromGramems(dict, grammems));
	}

	private static BitSet getBitSetFromGramems(final MorphDictionary dict,
			final String[] grammems) {
		Builder wfBuilder = Wordform.builder(dict, 0);
		for (String grammeme : grammems) {
			wfBuilder = wfBuilder.addGrammeme(grammeme);
		}
		return wfBuilder.buildSimple().getGrammems();
	}

	@Override
	public BitSet getValue() {
		return (BitSet) super.getValue();
	}

	public static TemplateElement[] getElements(
			final TemplateElement[] alphabet, final BitSet grammems) {
		boolean[] suitable = new boolean[alphabet.length];
		int n = 0;
		for (int i = 0; i < alphabet.length; i++) {
			BitSet curGrammems = (BitSet) ((BitSet) alphabet[i].getValue())
					.clone();
			curGrammems.and(grammems);
			if (curGrammems.equals(alphabet[i].getValue())) {
				suitable[i] = true;
				n++;
			}
		}

		TemplateElement[] result = new TemplateElement[n];
		for (int i = 0; i < alphabet.length; i++) {
			if (suitable[i]) {
				result[--n] = alphabet[i];
			}
		}
		return result;
	}
}