package ru.misis.asu.nlp.morphoanalysis;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.LongArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Logger;

import ru.misis.asu.nlp.commons.cas.FSUtils;
import ru.misis.asu.nlp.commons.exceptions.ExceptionHandler;
import ru.misis.asu.nlp.morphoanalysis.model.Lemma;
import ru.misis.asu.nlp.morphoanalysis.model.Wordform;
import ru.misis.asu.nlp.morphoanalysis.model.Wordform.Builder;
import ru.misis.asu.nlp.morphoanalysis.resource.MorphDictionary;
import ru.misis.asu.nlp.morphoanalysis.resource.SerializedDictionaryResource;
import ru.misis.asu.nlp.morphoanalysis.types.Word;
import ru.misis.asu.nlp.tokenization.types.Token;

public class MorphoanalysisAnnotator extends JCasAnnotator_ImplBase {
	private static final String TOKEN_TYPE_PARAM = "TokenType";
	private static final String MORPH_DICTIONARY_RESOURCE = "MorphDictionary";

	private static Logger logger;

	private static MorphDictionary dict;

	private String tokenTypeName;

	@Override
	public void reconfigure() throws ResourceConfigurationException,
			ResourceInitializationException {
		super.reconfigure();
	}

	@Override
	public void initialize(UimaContext ctx)
			throws ResourceInitializationException {
		super.initialize(ctx);
		logger = ctx.getLogger();
		tokenTypeName = (String) ctx.getConfigParameterValue(TOKEN_TYPE_PARAM);
		try {
			SerializedDictionaryResource dictResource = (SerializedDictionaryResource) ctx
					.getResourceObject(MORPH_DICTIONARY_RESOURCE);
			dict = dictResource.getDictionary();
		} catch (ResourceAccessException e) {
			ExceptionHandler.LogAndRethrow(logger,
					"Morph dictionary initialization error: ", e);
		}
	}

	@Override
	public void process(JCas cas) {
		Type tokenType = cas.getTypeSystem().getType(tokenTypeName);
		AnnotationIndex<Annotation> tokenIdx = cas
				.getAnnotationIndex(tokenType);
		for (Annotation token : tokenIdx) {
			String tokenStr = ((Token) token).getNorm();
			if (tokenStr != null) {
				List<Wordform> wfDictEntries = dict.getEntries(tokenStr);
				if (wfDictEntries != null && !wfDictEntries.isEmpty()) {
					makeWordAnnotation(cas, token, wfDictEntries);
				}
			}
		}
	}

	private void makeWordAnnotation(JCas cas, Annotation token,
			List<Wordform> wfDictEntries) {
		Word word = new Word(cas);
		word.setBegin(token.getBegin());
		word.setEnd(token.getEnd());
		List<ru.misis.asu.nlp.morphoanalysis.types.Wordform> casWfList = new LinkedList<>();
		for (Wordform wf : wfDictEntries) {
			ru.misis.asu.nlp.morphoanalysis.types.Wordform casWf = new ru.misis.asu.nlp.morphoanalysis.types.Wordform(
					cas);

			BitSet grammems = wf.getGrammems();
			int lemmaId = wf.getLemmaId();
			Lemma lemma = dict.getLemma(lemmaId);
			casWf.setLemmaId(lemmaId);
			casWf.setLemma(lemma.getString());
			casWf.setPos(dict.getPos(lemma));
			grammems.or(lemma.getGrammems());
			grammems.andNot(dict.getPosBits());
			List<String> gramSet = dict.toGramSet(grammems);
			casWf.setGrammems(FSUtils.toStringArray(cas, gramSet));

			Builder wfBuilder = Wordform.builder(dict, 0);
			for (String grammeme : gramSet) {
				wfBuilder = wfBuilder.addGrammeme(grammeme);
			}
			wfBuilder.addGrammeme(dict.getPos(lemma));
			long[] longarr = wfBuilder.buildSimple().getGrammems()
					.toLongArray();
			LongArray la = new LongArray(cas, longarr.length);
			la.copyFromArray(longarr, 0, 0, longarr.length);
			casWf.setGrammemBits(la);

			casWfList.add(casWf);
		}
		word.setWordforms(FSUtils.toFSArray(cas, casWfList));

		word.addToIndexes();
	}
}