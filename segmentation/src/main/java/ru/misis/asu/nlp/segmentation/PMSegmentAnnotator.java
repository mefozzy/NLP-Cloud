package ru.misis.asu.nlp.segmentation;

import java.util.Iterator;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import ru.misis.asu.nlp.segmentation.types.PMSegment;
import ru.misis.asu.nlp.segmentation.types.Sentence;
import ru.misis.asu.nlp.tokenization.types.PM;

public class PMSegmentAnnotator extends JCasAnnotator_ImplBase {
	private static final String PM_TYPE_PARAM = "PMType";
	private static final String SENTENCE_TYPE_PARAM = "SentenceType";

	private static String PMTypeName;
	private static String sentenceTypeName;

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		PMTypeName = (String) aContext.getConfigParameterValue(PM_TYPE_PARAM);
		sentenceTypeName = (String) aContext
				.getConfigParameterValue(SENTENCE_TYPE_PARAM);
	}

	@Override
	public void process(final JCas arg0) {
		Type PMType = arg0.getTypeSystem().getType(PMTypeName);
		Type sentenceType = arg0.getTypeSystem().getType(sentenceTypeName);

		AnnotationIndex<Annotation> sentences = arg0
				.getAnnotationIndex(sentenceType);
		AnnotationIndex<Annotation> PMSegments = arg0
				.getAnnotationIndex(PMType);

		Iterator<Annotation> sentenceIterator = sentences.iterator();
		while (sentenceIterator.hasNext()) {
			Sentence sentence = (Sentence) sentenceIterator.next();
			Iterator<Annotation> PMIterator = PMSegments.subiterator(sentence);
			PM prev = null;
			while (PMIterator.hasNext()) {
				PM pm = (PM) PMIterator.next();
				if (prev == null) {
					makePMAnnotation(arg0, sentence.getBegin(), pm.getBegin(),
							pm, sentence);
				} else {
					makePMAnnotation(arg0, prev.getEnd() + 1, pm.getBegin(),
							pm, sentence);
				}
				prev = pm;
			}
			if (prev == null) {
				makePMAnnotation(arg0, sentence.getBegin(), sentence.getEnd(),
						sentence.getEos(), sentence);
			} else {
				makePMAnnotation(arg0, prev.getEnd() + 1, sentence.getEnd(),
						sentence.getEos(), sentence);
			}
		}
	}

	private static void makePMAnnotation(final JCas cas, final int begin,
			final int end, final PM eopm, final Sentence sentence) {
		PMSegment segment = new PMSegment(cas);
		segment.setBegin(begin);
		segment.setEnd(end);
		segment.setEopm(eopm);
		segment.setSentence(sentence);
		segment.addToIndexes();
	}
}
