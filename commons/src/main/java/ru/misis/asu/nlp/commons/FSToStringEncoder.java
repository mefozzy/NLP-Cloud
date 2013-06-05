package ru.misis.asu.nlp.commons;

import org.apache.uima.cas.FeatureStructure;


public interface FSToStringEncoder {
	String getString(FeatureStructure fs);
}
