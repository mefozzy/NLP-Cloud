package ru.misis.asu.nlp.npr;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;

import ru.misis.asu.nlp.commons.algo.AhoCorasick;
import ru.misis.asu.nlp.commons.algo.Template;
import ru.misis.asu.nlp.commons.algo.TemplateElement;
import ru.misis.asu.nlp.commons.io.FileUtils;
import ru.misis.asu.nlp.morphoanalysis.resource.MorphDictionary;
import ru.misis.asu.nlp.morphoanalysis.resource.SerializedDictionaryResource;
import ru.misis.asu.nlp.morphoanalysis.types.Word;
import ru.misis.asu.nlp.morphoanalysis.types.Wordform;
import ru.misis.asu.nlp.npr.types.NounPhrase;
import ru.misis.asu.nlp.segmentation.types.PMSegment;

public class NPAnnotator extends JCasAnnotator_ImplBase {
	private static final String WORD_TYPE_PARAM = "WordType";
	private static final String PM_SEGMENT_TYPE_PARAM = "PMSegmentType";

	private static String templatesFileName = "templates/np.txt";
	private static NPTemplateEncoder encoder;
	private static AhoCorasick automaton;

	private static String wordTypeName;
	private static String PMSegmentTypeName;

	private SerializedDictionaryResource dictResource;
	private MorphDictionary dict;

	@Override
	public void initialize(final UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);

		wordTypeName = (String) aContext
				.getConfigParameterValue(WORD_TYPE_PARAM);
		PMSegmentTypeName = (String) aContext
				.getConfigParameterValue(PM_SEGMENT_TYPE_PARAM);

		try {
			dictResource = (SerializedDictionaryResource) aContext
					.getResourceObject("MorphDictionary");
		} catch (ResourceAccessException e1) {
			throw new RuntimeException(e1);
		}
		dict = dictResource.getDictionary();

		File templatesFile = null;
		try {
			templatesFile = new File(NPAnnotator.class.getClassLoader()
					.getResource(templatesFileName).toURI());
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		encoder = new NPTemplateEncoder(FileUtils.readLines(templatesFile),
				dict);
		automaton = encoder.getAutomaton();
	}

	@Override
	public void process(final JCas arg0) {
		TypeSystem ts = arg0.getTypeSystem();
		Type wordType = ts.getType(wordTypeName);
		Type segmentType = ts.getType(PMSegmentTypeName);

		AnnotationIndex<Annotation> words = arg0.getAnnotationIndex(wordType);
		AnnotationIndex<Annotation> segments = arg0
				.getAnnotationIndex(segmentType);

		try {
			Iterator<Annotation> segmentIterator = segments.iterator();
			while (segmentIterator.hasNext()) {
				PMSegment segment = (PMSegment) segmentIterator.next();
				Iterator<Annotation> wordIterator = words.subiterator(segment);
				processSegment(arg0, wordIterator, encoder.getAlphabet());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void processSegment(final JCas cas,
			final Iterator<Annotation> wordIterator,
			final TemplateElement[] alphabet) {
		Set<AhoCorasick.Node> states = new HashSet<>();
		states.add(automaton.getRoot());
		List<Word> words = new ArrayList<>();
		while (wordIterator.hasNext()) {
			Word word = (Word) wordIterator.next();
			words.add(word);
			Set<AhoCorasick.Node> nextStates = new HashSet<>();
			Set<Template> matchedTemplates = new HashSet<>();
			nextStates.add(automaton.getRoot());
			for (AhoCorasick.Node state : states) {
				FSArray wordforms = word.getWordforms();
				for (int i = 0; i < wordforms.size(); i++) {
					Wordform wordform = (Wordform) wordforms.get(i);

					TemplateElement[] telements = NPTemplateElement
							.getElements(alphabet, BitSet.valueOf(wordform
									.getGrammemBits().toArray()));
					for (TemplateElement element : telements) {
						AhoCorasick.Node nextState = automaton.getNext(state,
								element);
						Set<Template> outputs = nextState.getOutputs();
						matchedTemplates.addAll(outputs);
						nextStates.add(nextState);
					}
				}
			}

			for (Template template : matchedTemplates) {
				addNPAnnotation(cas, (NPTemplate) template, words);
			}
			states = nextStates;
		}
	}

	public static void addNPAnnotation(final JCas cas,
			final NPTemplate template, final List<Word> words) {
		int offset = words.size() - template.size();

		NounPhrase phrase = new NounPhrase(cas);

		if (template.getHeadInd() < 0) {
			throw new RuntimeException("Head index in template < 0");
		}
		int headInd = offset + template.getHeadInd();
		Word head = words.get(headInd);
		phrase.setHead(head);

		phrase.setBegin(head.getBegin());
		phrase.setEnd(head.getEnd());

		FSArray dependents = new FSArray(cas, template.size() - 1);
		int j = 0;
		for (int i = 0; i < template.size(); i++) {
			int wordInd = offset + i;
			if (i != template.getHeadInd()) {
				dependents.set(j++, words.get(wordInd));
			}
		}
		phrase.setDependents(dependents);

		if (template.getParticleInd() >= 0) {
			int particleInd = offset + template.getParticleInd();
			Word particle = words.get(particleInd);
			phrase.setParticle(particle);
		}

		if (template.getPrepositionInd() >= 0) {
			int prepositionInd = offset + template.getPrepositionInd();
			Word preposition = words.get(prepositionInd);
			phrase.setPreposition(preposition);
		}

		phrase.addToIndexes();
	}
}
