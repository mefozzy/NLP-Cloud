/**
 * 
 */
package ru.misis.asu.nlp.morphoanalysis.resource;

import java.util.BitSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import ru.misis.asu.nlp.morphoanalysis.model.Grammeme;
import ru.misis.asu.nlp.morphoanalysis.model.Lemma;

/**
 * @author Rinat Gareev (Kazan Federal University)
 * 
 */
public class LemmaByGrammemFilter implements LemmaFilter {

	private Set<String> grammemsToReject;

	public LemmaByGrammemFilter(String... grammemsToReject) {
		this.grammemsToReject = ImmutableSet.copyOf(grammemsToReject);
	}

	@Override
	public boolean accept(MorphDictionary dict, Lemma lemma) {
		BitSet grBits = lemma.getGrammems();
		for (int i = grBits.nextSetBit(0); i >= 0; i = grBits.nextSetBit(i + 1)) {
			Grammeme gr = dict.getGrammem(i);
			if (grammemsToReject.contains(gr.getId())) {
				return false;
			}
		}
		return true;
	}

}