/**
 * 
 */
package ru.misis.asu.nlp.morphoanalysis.resource;

import java.util.BitSet;
import java.util.List;
import java.util.Map;

import ru.misis.asu.nlp.morphoanalysis.model.Grammeme;
import ru.misis.asu.nlp.morphoanalysis.model.Lemma;
import ru.misis.asu.nlp.morphoanalysis.model.LemmaLinkType;
import ru.misis.asu.nlp.morphoanalysis.model.Wordform;

/**
 * @author Rinat Gareev (Kazan Federal University)
 * 
 */
public interface MorphDictionary {

    void setWfPredictor(WordformPredictor wfPredictor);

	List<Wordform> getEntries(String str);

	String getVersion();

	String getRevision();

	LemmaLinkType getLemmaLinkType(short id);

	int getGrammemMaxNumId();

	int getGrammemNumId(String gramId);

	Grammeme getGrammem(int numId);

	/**
	 * @param lemmaId
	 * @return lemma with given id
	 * @throws IllegalStateException
	 *             if lemma with given id is not found
	 */
    Lemma getLemma(int lemmaId);

    void addLemma(Lemma lemma);

	String getPos(Lemma lemma);

	BitSet getPosBits();

	Grammeme getGrammem(String id);

	Map<Integer, LemmaLinkType> getLemmaOutlinks(int lemmaId);

	Map<Integer, LemmaLinkType> getLemmaInlinks(int lemmaId);

	/**
	 * @param gramId
	 * @param includeTarget
	 *            if true given gramId will be included in result set
	 * @return
	 */
	BitSet getGrammemWithChildrenBits(String gramId, boolean includeTarget);

	List<String> toGramSet(BitSet grammems);

	BitSet internWordformGrammems(BitSet src);

	BitSet internLemmaGrammems(BitSet src);
}