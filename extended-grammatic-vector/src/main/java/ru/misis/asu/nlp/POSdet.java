package ru.misis.asu.nlp;

import ru.misis.asu.nlp.morphoanalysis.types.Word;
import ru.misis.asu.nlp.morphoanalysis.types.Wordform;

public class POSdet {
	public POSdet() { }
	
	boolean isEqual(Word w, String POS) {
		for(int i = 0; i < w.getWordforms().size(); i++)
		{
			if(w.getWordforms(i).getPos().equals(POS)) return true;
		}
		
		return false;
	}
}
