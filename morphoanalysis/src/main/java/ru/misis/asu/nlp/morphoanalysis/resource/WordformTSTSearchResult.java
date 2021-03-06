package ru.misis.asu.nlp.morphoanalysis.resource;


import ru.misis.asu.nlp.morphoanalysis.model.Wordform;

import java.util.Iterator;

public class WordformTSTSearchResult implements Iterable<Wordform>{
    private boolean isMatchExact;
    private int matchLength;
    private Iterator<Wordform> wordformIterator;

    public WordformTSTSearchResult(boolean matchExact, int matchLength, Iterator<Wordform> wordformIterator) {
        isMatchExact = matchExact;
        this.matchLength = matchLength;
        this.wordformIterator = wordformIterator;
    }

    public boolean isMatchExact() {
        return isMatchExact;
    }

    public int getMatchLength() {
        return matchLength;
    }

    @Override
    public Iterator<Wordform> iterator() {
        return wordformIterator;
    }
}
