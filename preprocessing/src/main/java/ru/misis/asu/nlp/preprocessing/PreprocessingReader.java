package ru.misis.asu.nlp.preprocessing;

import java.io.File;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Logger;

import ru.misis.asu.nlp.commons.exceptions.ExceptionHandler;

public class PreprocessingReader extends JCasAnnotator_ImplBase {
	private static final String INPUT_FILE_PARAM = "InputFile";

	private static Logger logger;

	private File file;

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		logger = aContext.getLogger();
		String fileName = (String) aContext
				.getConfigParameterValue(INPUT_FILE_PARAM);
		file = new File(fileName);
		if (!file.exists()) {
			ExceptionHandler.LogAndThrow(logger, "Input file does not exists");
		}
	}

	@Override
	public void process(JCas jcas) {
		TikaProcessor processor = new TikaProcessor();
		try {
			processor = TikaProcessor.newInstance(file);
		} catch (Exception e) {
			ExceptionHandler.LogAndRethrow(logger, "TikaProcessor: ", e);
		}

		String documentText = processor.getText();
		if (documentText == null || documentText.length() == 0) {
			ExceptionHandler.LogAndThrow(logger,
					"Document text is null or empty");
		}
		jcas.setDocumentText(documentText);

		String textLanguage = processor.getLanguage();
		if (!textLanguage.contains("ru")) {
			ExceptionHandler.LogAndThrow(logger,
					"Document language is not russian");
		}
		jcas.setDocumentLanguage(textLanguage);
	}
}
