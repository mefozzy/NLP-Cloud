package ru.misis.asu.nlp.preprocessing;

import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Logger;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;
import ru.misis.asu.nlp.commons.exceptions.ExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.net.URI;


/**
 You can use it to read info from single file. InputFile param contains file URI in string representation
 */
public class SingleFileCollectionReader extends CollectionReader_ImplBase {
	private static final String INPUT_FILE_PARAM = "InputFile";

	private static Logger logger;

	private File file;
	private int fileCount = 1;
	private int currentFile = 0;

	@Override
	public void initialize()
			throws ResourceInitializationException {
		super.initialize();
		logger = getLogger();
		String fileName = (String) getConfigParameterValue(INPUT_FILE_PARAM);

		try {
			file = new File(URI.create(fileName));
		} catch(Exception e) {
			ExceptionHandler.logAndRethrow(logger, e);
		}

		if (!file.exists()) {
			ExceptionHandler.logAndThrow(logger, "Input file does not exists");
		}
	}

	@Override
	public void getNext(final CAS aCAS) throws IOException, CollectionException {
		TikaProcessor processor = new TikaProcessor();
		try {
			processor = TikaProcessor.newInstance(file);
		} catch (Exception e) {
			ExceptionHandler.logAndRethrow(logger, "TikaProcessor: ", e);
		}

		String documentText = processor.getText();
		if (documentText == null || documentText.length() == 0) {
			ExceptionHandler.logAndThrow(logger, "Document text is null or empty");
		}
		aCAS.setDocumentText(documentText);

		String textLanguage = processor.getLanguage();
		if (!textLanguage.contains("ru")) {
			ExceptionHandler.logAndThrow(logger, "Document language is not russian");
		}
		aCAS.setDocumentLanguage(textLanguage);
	}

	@Override
	public boolean hasNext() throws IOException, CollectionException {
		return (currentFile < fileCount);
	}

	@Override
	public Progress[] getProgress() {
		return new Progress[] { new ProgressImpl(currentFile, fileCount, Progress.ENTITIES) };
	}

	@Override
	public void close() throws IOException {
	}
}
