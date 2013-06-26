package ru.misis.asu.nlp;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import ru.misis.asu.nlp.morphoanalysis.types.Word;
import ru.misis.asu.nlp.morphoanalysis.types.Wordform;


public class Analyzer {
	HashMap<String, ExtendedPreposition> map;
	LinkedList<ExtendedWord> lst;
	JCas aJCas;
	POSdet pd = new POSdet();
	
	//SHOULD BE DELETED
	java.io.FileWriter fw;
	String newLine = System.getProperty("line.separator");
	//TO DELETE
	 
	public Analyzer(HashMap<String, ExtendedPreposition> map, JCas aJCas){
		this.map = map;
		this.aJCas = aJCas;
	}
	//My Handlers
	Set<String> extraWords = new HashSet<>(Arrays.asList("хотя", "хоть", "лишь"));
	int lastVerbindex;
	int not_found = -100;
	ExtendedWord exW;
	Service gottenPREP, gottenNPRCL, gottenIPRCL;
	
	private int prepositionHandler(Word w) {
		if(gottenPREP != null)
		{
			if(pd.isEqual(w, "ADJF") || pd.isEqual(w, "PRTF") || pd.isEqual(w, "NUMR"))
			{
				exW.setPreposition((Preposition)gottenPREP);
			}
			if(pd.isEqual(w, "NOUN") || pd.isEqual(w, "NPRO"))
			{
				exW.setPreposition((Preposition)gottenPREP);
				
				gottenPREP = null;
			}
		}
		else if(pd.isEqual(w, "PREP"))
		{
			if(map.containsKey(w.getWordforms(0).getLemma())) {
				gottenPREP = (map.get(w.getWordforms(0).getLemma())).getPreposition(aJCas, w);
				return 1;
			}
		}
		return 0;
	}
	
	private int negativeParticleHandler(Word w) {
		if(gottenNPRCL != null)
		{
			if(pd.isEqual(w, "NOUN") || pd.isEqual(w, "VERB") || pd.isEqual(w, "INFN") || pd.isEqual(w, "ADVB"))
			{
				exW.setNegativeParticle((NegativeParticle)gottenNPRCL);
				
				gottenNPRCL = null;
			}
		}
		else if(pd.isEqual(w, "PRCL"))
		{
			String val = w.getWordforms(0).getLemma().toLowerCase();
			if(val.equals("не") || val.equals("ни")) {
				gottenNPRCL = new NegativeParticle(aJCas);
				gottenNPRCL.setWord(w);
				gottenNPRCL.setBegin(w.getBegin());
				gottenNPRCL.setEnd(w.getEnd());
				return 1;
			}
		}
		return 0;
	}
	
	private int inclinationParticleHandler(Word w) {
		String val = w.getWordforms(0).getLemma().toLowerCase();
		if(pd.isEqual(w, "VERB") || pd.isEqual(w, "INFN") || extraWords.contains(val)) {
			if(gottenIPRCL != null)
			{
				if(lastVerbindex != not_found && !(lastVerbindex == lst.size() - 1 && pd.isEqual(w, "VERB") && pd.isEqual(lst.get(lastVerbindex).getWord(), "INFN")))
					lst.get(lastVerbindex).setInclinationParticle((InclinationParticle)gottenIPRCL);				
				else
					exW.setInclinationParticle((InclinationParticle)gottenIPRCL);
				
				gottenIPRCL = null;
			}
			else
			{
				lastVerbindex = lst.size();
			}
		}
		else if(pd.isEqual(w, "PRCL")) {
			if(val.equals("бы") || val.equals("б")) {
				gottenIPRCL = new InclinationParticle(aJCas);
				gottenIPRCL.setWord(w);
				gottenIPRCL.setBegin(w.getBegin());
				gottenIPRCL.setEnd(w.getEnd());
				
				if(lastVerbindex != lst.size() - 1) lastVerbindex = not_found;
				
				return 1;
			}
		}
		
		return 0;
	}
	//

	public void process(final Iterator<Annotation> iter, java.io.FileWriter fw) {	//DELETE fw Token
		//SHOULD BE DELETED
		this.fw = fw;
		//TO DELETE
		
		lastVerbindex = not_found;
		lst = new LinkedList<ExtendedWord>();
		gottenPREP = null; gottenNPRCL = null; gottenIPRCL = null;
		
		while(iter.hasNext())
		{
			Word w = (Word)iter.next();
			exW = new ExtendedWord(aJCas);
			exW.setWord(w);
			exW.setBegin(w.getBegin());
			exW.setEnd(w.getEnd());
			
			if(prepositionHandler(w) == 1)
				continue;
			
			if(negativeParticleHandler(w) == 1)
				continue;
			
			if(inclinationParticleHandler(w) == 1)
				continue;
			
			lst.add(exW);
			
			//SHOULD BE DELETED	
			if(w.getWordforms(0).getLemma().length() <= 5) {
				try {
					fw.write(w.getWordforms(0).getLemma() + newLine);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//TO DELETE
		}
		
		for(int i = 0; i < lst.size(); i++)
			aJCas.addFsToIndexes(lst.get(i));
	}
}
