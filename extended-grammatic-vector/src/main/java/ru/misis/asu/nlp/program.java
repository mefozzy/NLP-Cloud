package ru.misis.asu.nlp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

public class program extends JCasAnnotator_ImplBase {
	static HashMap<String, ExtendedPreposition> map;
	
	//SHOULD BE DELETED
	java.io.FileWriter fw;
	//TO DELETE
	
	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		map = new HashMap<String, ExtendedPreposition>();
		XmlReader xReader = new XmlReader();
		map = xReader.load(program.class.getClassLoader().getResource((String)aContext.getConfigParameterValue("filePrepositionPath")));
		
		//SHOULD BE DELETED
		try {
		fw = new java.io.FileWriter("D:\\output.txt");
		} catch(Exception ex){ throw new RuntimeException(ex); }
		//TO DELETE
	}

	@Override
	public void process(final JCas aJCas) throws AnalysisEngineProcessException {
		TypeSystem ts = aJCas.getTypeSystem();
		Type tw = ts.getType("ru.misis.asu.nlp.morphoanalysis.types.Word");
		Type t = ts.getType("ru.misis.asu.nlp.segmentation.types.Sentence");
		AnnotationIndex<Annotation> indw = aJCas.getAnnotationIndex(tw);
		AnnotationIndex<Annotation> inds = aJCas.getAnnotationIndex(t);

		Analyzer an = new Analyzer(map, aJCas);

		for (Iterator<Annotation> it = inds.iterator(); it.hasNext();) {
			FSIterator<Annotation> iter = indw.subiterator(it.next());
			
			an.process(iter, fw);
		}
		
		//SHOULD BE DELETED
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TO DELETE
	}

}
