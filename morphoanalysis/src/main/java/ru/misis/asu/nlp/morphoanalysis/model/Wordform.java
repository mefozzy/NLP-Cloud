package ru.misis.asu.nlp.morphoanalysis.model;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Objects;

import ru.misis.asu.nlp.morphoanalysis.resource.MorphDictionary;

/**
 * @author Rinat Gareev (Kazan Federal University)
 */
public class Wordform implements Serializable {

	private static final long serialVersionUID = -8435061415886880938L;
	private static final BitSet EMPTY_BITSET = new BitSet();

	public static Builder builder(MorphDictionary dict, int lemmaId) {
		return new Builder(dict, lemmaId);
	}

	public static class Builder {
		private Wordform instance = new Wordform();
		private MorphDictionary dict;

		public Builder(final MorphDictionary dict, final int lemmaId) {
			instance.lemmaId = lemmaId;
			this.dict = dict;
		}

		public Builder addGrammeme(final String gramId) {
			if (instance.grammems == null) {
				instance.grammems = new BitSet(dict.getGrammemMaxNumId() + 1);
			}
			int gramNumId = dict.getGrammemNumId(gramId);
			instance.grammems.set(gramNumId);
			return this;
		}

		public Wordform buildSimple() {
			return instance;
		}

		public Wordform build() {
			if (instance.grammems == null) {
				instance.grammems = EMPTY_BITSET;
			} else {
				instance.grammems = dict
						.internWordformGrammems(instance.grammems);
			}
			return instance;
		}
	}

	private int lemmaId;
	private BitSet grammems;

	private Wordform() {
	}

	public Wordform(int lemmaId, BitSet grammems) {
		this.lemmaId = lemmaId;
		this.grammems = grammems;
	}

	public int getLemmaId() {
		return lemmaId;
	}

	public Wordform cloneWithLemmaId(int newLemmaId) {
		return new Wordform(newLemmaId, grammems);
	}

	public BitSet getGrammems() {
		return (BitSet) grammems.clone();
	}

	@Override
	public int hashCode() {
		return Objects.hash(lemmaId, grammems);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Wordform)) {
			return false;
		}
		Wordform wordform = (Wordform) obj;
		return Objects.equals(lemmaId, wordform.lemmaId)
				&& Objects.equals(grammems, wordform.grammems);
	}

}