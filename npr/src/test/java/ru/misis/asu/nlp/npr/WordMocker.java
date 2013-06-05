package ru.misis.asu.nlp.npr;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.StringArray;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import ru.misis.asu.nlp.morphoanalysis.types.Word;
import ru.misis.asu.nlp.morphoanalysis.types.Wordform;


public class WordMocker {
	private WordMocker() {
		throw new UnsupportedOperationException();
	}

	public static Word getMock(final String[][] array) {
		Word mock = PowerMockito.mock(Word.class);

		// Here I need PowerMock because of UIMA's "elegant" architecture.
		// They made a high coupled link on a final class, so I can't do this:
		// FSArray wordformsFS = Mockito.mock(FSArray.class);

		FSArray wordformsFS = PowerMockito.mock(FSArray.class);
		Mockito.when(wordformsFS.size()).thenReturn(array.length);
		for (int i = 0; i < array.length; i++) {
			Wordform wordformMock = PowerMockito.mock(Wordform.class);
			StringArray grammems = PowerMockito.mock(StringArray.class);
			Mockito.when(grammems.size()).thenReturn(array[i].length);
			for (int j = 0; j < array[i].length; j++) {
				Mockito.when(grammems.get(j)).thenReturn(array[i][j]);
			}
			Mockito.when(wordformMock.getGrammems()).thenReturn(grammems);
			Mockito.when(wordformsFS.get(i)).thenReturn(wordformMock);
		}
		Mockito.when(mock.getWordforms()).thenReturn(wordformsFS);
		return mock;
	}
}
