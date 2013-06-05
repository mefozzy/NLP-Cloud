package ru.misis.asu.nlp.segmentation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import ru.misis.asu.nlp.segmentation.types.Sentence;
import ru.misis.asu.nlp.tokenization.types.PM;

public class SentenceAnnotator extends JCasAnnotator_ImplBase {
	private static final String PM_TYPE_PARAM = "PMType";

	private static String PMTypeName;

	private final Set<String> eos = new HashSet<>(Arrays.asList("?", "!", ".",
			"...", "?!"));

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		PMTypeName = (String) aContext.getConfigParameterValue(PM_TYPE_PARAM);
	}

	@Override
	public void process(final JCas arg0) {
		Type PMType = arg0.getTypeSystem().getType(PMTypeName);

		AnnotationIndex<Annotation> PMs = arg0.getAnnotationIndex(PMType);

		Iterator<Annotation> PMIterator = PMs.iterator();
		PM prev = null;
		while (PMIterator.hasNext()) {
			PM pm = (PM) PMIterator.next();
			if (isEOS(pm)) {
				if (prev == null) {
					makeSentenceAnnotation(arg0, 0, pm.getBegin(), pm);
				} else {
					makeSentenceAnnotation(arg0, prev.getEnd() + 1,
							pm.getBegin(), pm);
				}
				prev = pm;
			}
		}
	}

	private boolean isEOS(final PM token) {
		return eos.contains(token.getCoveredText());
	}

	private static void makeSentenceAnnotation(final JCas cas, final int begin,
			final int end, final PM eos) {
		Sentence sentence = new Sentence(cas);
		sentence.setBegin(begin);
		sentence.setEnd(end);
		sentence.setEos(eos);
		sentence.addToIndexes();
	}
}