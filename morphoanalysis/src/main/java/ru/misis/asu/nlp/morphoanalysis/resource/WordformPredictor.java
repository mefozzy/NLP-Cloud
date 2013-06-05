package ru.misis.asu.nlp.morphoanalysis.resource;

import ru.misis.asu.nlp.morphoanalysis.model.Wordform;

import java.util.List;

public interface WordformPredictor {
    List<Wordform> predict(String str, WordformTSTSearchResult result);
}
