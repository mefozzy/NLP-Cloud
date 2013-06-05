/**
 * 
 */
package ru.misis.asu.nlp.morphoanalysis.model;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Objects;

import ru.misis.asu.nlp.morphoanalysis.resource.MorphDictionary;

/**
 * @author Rinat Gareev (Kazan Federal University)
 */
public class Lemma implements Serializable {

	private static final long serialVersionUID = 7426278009038784123L;
	private static final BitSet EMPTY_BITSET = new BitSet();

	public static Builder builder(MorphDictionary dict, int id) {
		return new Builder(dict, id);
	}

	public static class Builder {
		private Lemma instance = new Lemma();
		private MorphDictionary dict;

		public Builder(MorphDictionary dict, int id) {
			instance.id = id;
			this.dict = dict;
		}

		public int getLemmaId() {
			return instance.id;
		}

		public Builder setString(String string) {
			instance.string = string;
			return this;
		}

		public Builder addGrammeme(String gramId) {
			if (instance.grammems == null) {
				instance.grammems = new BitSet(dict.getGrammemMaxNumId() + 1);
			}
			int gramNumId = dict.getGrammemNumId(gramId);
			instance.grammems.set(gramNumId);
			return this;
		}

		public Lemma build() {
			if (instance.string == null) {
				throw new IllegalStateException("'string' is null");
			}
			if (instance.grammems == null) {
				instance.grammems = EMPTY_BITSET;
			} else {
				instance.grammems = dict.internLemmaGrammems(instance.grammems);
			}

			return instance;
		}
	}

	private int id;
	private String string;

	public Lemma() {
	}

	public Lemma(String string, BitSet grammems) {
		this.string = string;
		this.grammems = grammems;
	}

	private BitSet grammems;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getString() {
		return string;
	}

	public BitSet getGrammems() {
		return (BitSet) grammems.clone();
	}

	public Lemma cloneWithoutIdAndString() {
		return new Lemma("", grammems);
	}

	@Override
	public String toString() {
		return string + ":" + id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(string, grammems);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Lemma)) {
			return false;
		}
		Lemma lemma = (Lemma) obj;
		return Objects.equals(string, lemma.string)
				&& Objects.equals(grammems, lemma.grammems);
	}
}