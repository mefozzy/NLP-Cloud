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
import java.util.ArrayList;


/**
You can use it to read a list of files from a directory. InputDirectory param contains string representation of directory URI
 */
public class FileSystemCollectionReader extends CollectionReader_ImplBase{
	public static final String INPUT_DIRECTORY_PARAM       = "InputDirectory";
	public static final String SEARCH_SUBDIRECTORIES_PARAM = "SearchSubdirectories";

	private static Logger logger;

	private ArrayList<File> files;
	private int currentFile = 0;
	private boolean searchSubdirectories;

	@Override
	public void initialize() throws ResourceInitializationException {
		super.initialize();
		logger = getLogger();
		searchSubdirectories = (boolean) getConfigParameterValue(SEARCH_SUBDIRECTORIES_PARAM);
		File dir = new File(URI.create((String) getConfigParameterValue(INPUT_DIRECTORY_PARAM)));
		if (!dir.exists() || !dir.isDirectory()) {
			ExceptionHandler.logAndThrow(logger, "Input directory does not exists or not a directory");
		}
		addFiles(dir);
	}

	public void addFiles(File dir) {
		File[] fileList = dir.listFiles();
		if (fileList != null) {
			for (File file: fileList) {
				if (!file.isDirectory()) {
					files.add(file);
				} else if (searchSubdirectories) {
					addFiles(file);
				}
			}
		}
	}

	@Override
	public void getNext(final CAS aCAS) throws IOException, CollectionException {
		File file = files.get(currentFile);
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
		return (currentFile < files.size());
	}

	@Override
	public Progress[] getProgress() {
		return new Progress[] { new ProgressImpl(currentFile, files.size(), Progress.ENTITIES) };
	}

	@Override
	public void close() throws IOException {
	}
}
