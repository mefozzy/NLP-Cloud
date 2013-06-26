package ru.misis.asu.nlp;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.StringArray;
import ru.misis.asu.nlp.morphoanalysis.types.Word;

public class ExtendedPreposition {
	String spelling;
	String [] grammemes;
	
	public ExtendedPreposition(String _spl, String [] _Grm)
	{
		this.spelling = _spl;
		this.grammemes = _Grm;
	}
	
	public Preposition getPreposition(JCas aJCas, Word w)
	{
		Preposition p = new Preposition(aJCas);
		
		StringArray sArray = new StringArray(aJCas, grammemes.length);
		sArray.copyFromArray(grammemes, 0, 0, grammemes.length);
		
		p.setWord(w);
		p.setBegin(w.getBegin());
		p.setEnd(w.getEnd());
		p.setGrammemes(sArray);
		
		return p;
	}
}
