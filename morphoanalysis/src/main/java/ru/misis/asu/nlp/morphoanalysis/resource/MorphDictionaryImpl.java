/**
 * 
 */
package ru.misis.asu.nlp.morphoanalysis.resource;

import static com.google.common.collect.ImmutableMap.copyOf;
import static com.google.common.collect.Tables.unmodifiableTable;
import static java.lang.System.currentTimeMillis;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import ru.misis.asu.nlp.morphoanalysis.model.Grammeme;
import ru.misis.asu.nlp.morphoanalysis.model.Lemma;
import ru.misis.asu.nlp.morphoanalysis.model.LemmaLinkType;
import ru.misis.asu.nlp.morphoanalysis.model.Wordform;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

/**
 * @author Rinat Gareev (Kazan Federal University)
 * 
 */
public class MorphDictionaryImpl implements Serializable, MorphDictionary {

	private static final long serialVersionUID = -4108756831996589900L;

	private String version;
	private String revision;
	private Map<String, Grammeme> gramMap = Maps.newHashMap();
	private SortedMap<Integer, Grammeme> numToGram = Maps.newTreeMap();
	private Map<Integer, Lemma> lemmaMap = Maps.newHashMap();
	private Map<Short, LemmaLinkType> lemmaLinkTypeMap = Maps.newHashMap();
	// <from, to, type>
	private Table<Integer, Integer, LemmaLinkType> lemmaLinkTable = TreeBasedTable
			.create();

	private transient Map<BitSet, BitSet> uniqWordformGrammemsMap = Maps
			.newHashMap();
	private transient Map<BitSet, BitSet> uniqLemmaGrammemsMap = Maps
			.newHashMap();

	private HashMap<String, List<Wordform>> wfByString = new HashMap<>();

	@Override
	public void setWfPredictor(WordformPredictor wfPredictor) {
		this.wfPredictor = wfPredictor;
	}

	// wf indexes
	// by string
	private transient WordformPredictor wfPredictor;

	// grammem indexes
	// by parent
	private transient Multimap<String, Grammeme> gramByParent;
	private transient BitSet posBits;
	// state mark
	private transient boolean complete = false;

	@Override
	public List<Wordform> getEntries(String str) {
		return wfByString.get(str);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRevision() {
		return revision;
	}

	@Override
	public int getGrammemMaxNumId() {
		return numToGram.lastKey();
	}

	@Override
	public int getGrammemNumId(String gramId) {
		Grammeme gr = gramMap.get(gramId);
		if (gr == null) {
			noGrammem(gramId);
		}
		return gr.getNumId();
	}

	@Override
	public Grammeme getGrammem(int numId) {
		return numToGram.get(numId);
	}

	public void addGrammeme(Grammeme gram) {
		if (gramMap.put(gram.getId(), gram) != null) {
			throw new IllegalStateException(String.format(
					"Duplicate grammem id - %s", gram.getId()));
		}
		if (numToGram.put(gram.getNumId(), gram) != null) {
			throw new IllegalStateException(String.format(
					"Duplicate grammem num id - %s", gram.getNumId()));
		}
	}

	@Override
	public void addLemma(Lemma l) {
		if (lemmaMap.put(l.getId(), l) != null) {
			throw new IllegalStateException(String.format(
					"Duplicate lemma id - %s", l.getId()));
		}
	}

	public void addLemmaLinkType(LemmaLinkType linkType) {
		if (lemmaLinkTypeMap.put(linkType.getId(), linkType) != null) {
			throw new IllegalStateException(String.format(
					"Duplicate lemma link type - %s", linkType.getId()));
		}
	}

	private transient int invalidLinkCounter = 0;

	public void addLemmaLink(int from, int to, short linkTypeId) {
		if (!lemmaMap.containsKey(from)) {
			invalidLinkCounter++;
			return;
		}
		if (!lemmaMap.containsKey(to)) {
			invalidLinkCounter++;
			return;
		}
		LemmaLinkType linkType = getLemmaLinkType(linkTypeId);
		if (linkType == null) {
			noLemmaLinkType(linkTypeId);
		}
		lemmaLinkTable.put(from, to, linkType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LemmaLinkType getLemmaLinkType(short id) {
		return lemmaLinkTypeMap.get(id);
	}

	@Override
	public Lemma getLemma(int lemmaId) {
		Lemma result = lemmaMap.get(lemmaId);
		if (result == null) {
			throw new IllegalStateException(String.format(
					"No lemma with id = %s", lemmaId));
		}
		return result;
	}

	@Override
	public String getPos(Lemma lemma) {
		BitSet lGrams = lemma.getGrammems();
		lGrams.and(getPosBits());
		if (lGrams.isEmpty()) {
			return null;
		}
		if (lGrams.cardinality() > 1) {
			List<String> posList = Lists.newLinkedList();
			for (int i = lGrams.nextSetBit(0); i >= 0; i = lGrams
					.nextSetBit(i + 1)) {
				posList.add(getGrammem(i).getId());
			}
		}
		int gramNumId = lGrams.nextSetBit(0);
		Grammeme result = getGrammem(gramNumId);
		notNull(result);
		return result.getId();
	}

	@Override
	public BitSet getPosBits() {
		if (!complete) {
			throw new UnsupportedOperationException();
		}
		if (posBits == null) {
			posBits = getGrammemWithChildrenBits("POST", true);
			isTrue(!posBits.isEmpty());
		}
		return (BitSet) posBits.clone();
	}

	@Override
	public BitSet getGrammemWithChildrenBits(String gramId,
			boolean includeTarget) {
		Grammeme targetGram = getGrammem(gramId);
		BitSet result = new BitSet(getGrammemMaxNumId());
		if (includeTarget) {
			result.set(targetGram.getNumId());
		}
		for (Grammeme childGram : gramByParent.get(gramId)) {
			result.set(childGram.getNumId());
		}
		return result;
	}

	@Override
	public Grammeme getGrammem(String id) {
		Grammeme gr = gramMap.get(id);
		if (gr == null) {
			throw new IllegalStateException(String.format(
					"Unknown grammeme: %s", id));
		}
		return gr;
	}

	@Override
	public List<String> toGramSet(BitSet gramBits) {
		ImmutableList.Builder<String> rb = ImmutableList.builder();
		for (int i = gramBits.nextSetBit(0); i >= 0; i = gramBits
				.nextSetBit(i + 1)) {
			rb.add(getGrammem(i).getId());
		}
		return rb.build();
	}

	@Override
	public BitSet internWordformGrammems(BitSet grammems) {
		if (uniqWordformGrammemsMap.containsKey(grammems)) {
			return uniqWordformGrammemsMap.get(grammems);
		} else {
			uniqWordformGrammemsMap.put(grammems, grammems);
			return grammems;
		}
	}

	@Override
	public BitSet internLemmaGrammems(BitSet grammems) {
		if (uniqLemmaGrammemsMap.containsKey(grammems)) {
			return uniqLemmaGrammemsMap.get(grammems);
		} else {
			uniqLemmaGrammemsMap.put(grammems, grammems);
			return grammems;
		}
	}

	@Override
	public Map<Integer, LemmaLinkType> getLemmaOutlinks(int lemmaId) {
		return lemmaLinkTable.row(lemmaId);
	}

	@Override
	public Map<Integer, LemmaLinkType> getLemmaInlinks(int lemmaId) {
		return lemmaLinkTable.column(lemmaId);
	}

	public void addWordform(String text, Wordform wf) {
		if (wfByString.containsKey(text)) {
			wfByString.get(text).add(wf);
		} else {
			List<Wordform> lst = new ArrayList<>();
			lst.add(wf);
			wfByString.put(text, lst);
		}
	}

	void complete() {
		if (complete) {
			throw new IllegalStateException();
		}
		makeUnmodifiable();
		// uniqGrammemsMap = null;
		complete = true;
	}

	void setVersion(String version) {
		this.version = version;
	}

	void setRevision(String revision) {
		this.revision = revision;
	}

	@SuppressWarnings("unused")
	private void noLemma(int id) {
		throw new IllegalStateException(String.format(
				"Lemma with id = %s is not registered", id));
	}

	private void noLemmaLinkType(int id) {
		throw new IllegalStateException(String.format(
				"Lemma link type with id = %s is not registered", id));
	}

	private void noGrammem(String id) {
		throw new IllegalStateException(String.format(
				"Grammem with id = %s is not registered", id));
	}

	private void makeUnmodifiable() {
		gramMap = copyOf(gramMap);
		numToGram = ImmutableSortedMap.copyOf(numToGram);
		// lemmaMap = unmodifiableMap(lemmaMap);
		lemmaLinkTypeMap = copyOf(lemmaLinkTypeMap);
		lemmaLinkTable = unmodifiableTable(lemmaLinkTable);
	}

	private void buildIndices() {
		long timeBefore = currentTimeMillis();
		gramByParent = HashMultimap.create();
		for (Grammeme gr : gramMap.values()) {
			gramByParent.put(gr.getParentId(), gr);
		}
		posBits = null;
		getPosBits();
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		in.defaultReadObject();
		complete = true;
		buildIndices();
	}

	private static void notNull(Object obj) {
		if (obj == null) {
			throw new IllegalStateException("Unexpected null value");
		}
	}

	private static void isTrue(boolean condition) {
		if (!condition) {
			throw new IllegalStateException("Assertion failed");
		}
	}
}