/**
 * 
 */
package ru.misis.asu.nlp.morphoanalysis.resource;

import ru.misis.asu.nlp.morphoanalysis.model.Lemma;

/**
 * @author Rinat Gareev (Kazan Federal University)
 * 
 */
public interface LemmaFilter {

	boolean accept(MorphDictionary dict, Lemma lemma);
}