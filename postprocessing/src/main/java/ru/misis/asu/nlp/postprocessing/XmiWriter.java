package ru.misis.asu.nlp.postprocessing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.XMLSerializer;
import org.xml.sax.SAXException;

/**
 * @author Rinat Gareev (Kazan Federal University)
 */
public class XmiWriter extends JCasAnnotator_ImplBase {
	private static final String OUTPUT_FILE_PARAM = "OutputFile";

	private File outputFile;

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		String outputFileName = (String) aContext
				.getConfigParameterValue(OUTPUT_FILE_PARAM);
		outputFile = new File(outputFileName);
	}

	@Override
	public void process(JCas aCAS) throws AnalysisEngineProcessException {
		try {
			writeXmi(aCAS.getCas(), outputFile);
		} catch (IOException e) {
			throw new AnalysisEngineProcessException(e);
		} catch (SAXException e) {
			throw new AnalysisEngineProcessException(e);
		}
	}

	private void writeXmi(CAS aCas, File name) throws IOException, SAXException {
		FileOutputStream out = null;

		try {
			out = new FileOutputStream(name);
			XmiCasSerializer ser = new XmiCasSerializer(aCas.getTypeSystem());
			XMLSerializer xmlSer = new XMLSerializer(out, false);
			ser.serialize(aCas, xmlSer.getContentHandler());
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}