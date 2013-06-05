package ru.misis.asu.parent.utils;

import static java.lang.System.currentTimeMillis;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.XMLInputSource;

import com.google.common.io.Files;

import ru.misis.asu.nlp.commons.io.NIOReader;
import ru.misis.asu.nlp.commons.test.PrettyPrinter;
import ru.misis.asu.nlp.commons.test.PrettyPrinter.Builder;

/**
 * @author Rinat Gareev (Kazan Federal University)
 */
public class Launcher {
	public static void main(String[] args) throws IOException, UIMAException {
		// Simple args check
		if (args.length != 1) {
			System.err.println("Usage: <input-file>");
			return;
		}

		// Check input file. Get output directory
		File inputFile = new File(args[0]);
		if (!inputFile.isFile()) {
			System.err.println("Specified file does not exist");
			return;
		}
		if (Files.getFileExtension(args[0]).equals("xmi")) {
			System.err.println("File has .xmi extension");
			return;
		}
		File outputDir = inputFile.getParentFile();

		// Configure UIMA: set aggregate descriptor and parameters
		XMLInputSource aeDescInput = new XMLInputSource(
				"src/main/resources/uima.xml/Tokenization-test-aggregate-ae.xml");
		AnalysisEngineDescription aeDesc = UIMAFramework.getXMLParser()
				.parseAnalysisEngineDescription(aeDescInput);
		String outputFileName = outputDir.getPath() + "\\"
				+ inputFile.getName() + ".xmi";
		aeDesc.getAnalysisEngineMetaData().getConfigurationParameterSettings()
				.setParameterValue("OutputFile", outputFileName);
		String inputFileName = inputFile.getPath();
		aeDesc.getAnalysisEngineMetaData().getConfigurationParameterSettings()
				.setParameterValue("InputFile", inputFileName);

		AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aeDesc);

		// Read input file in UTF-8 encoding
		StringBuilder builder = new StringBuilder();
		byte[] inputData = NIOReader.readBytes(inputFile);
		try (BufferedReader in = new BufferedReader(new InputStreamReader(
				new ByteArrayInputStream(inputData), "UTF8"))) {
			String line;
			while ((line = in.readLine()) != null) {
				builder.append(line).append(
						System.getProperty("line.separator"));
			}
		} catch (Exception e) {
			System.err.println("Error while reading file");
		}

		// Produce analysis engine
		JCas cas = ae.newJCas();
	//	cas.setDocumentText(builder.toString());

		// Run
		long timeBefore = currentTimeMillis();
		ae.process(cas);
		System.out.println("Finished in " + (currentTimeMillis() - timeBefore)
				+ " ms");
		
		TypeSystem ts = cas.getTypeSystem();
		Type date = ts.getType("ru.misis.asu.nlp.tokenization.types.Date");
		Type range = ts.getType("ru.misis.asu.nlp.tokenization.types.Range");
		Type measurement = ts.getType("ru.misis.asu.nlp.tokenization.types.Measurement");
		Type word = ts.getType("ru.misis.asu.nlp.tokenization.types.ComplexWord");
		
		@SuppressWarnings("unchecked")
		PrettyPrinter pretty = new Builder(cas.getDocumentText(), 
				new Iterator[] {
			cas.getAnnotationIndex(word).iterator()
		}).typeNames("ComplexWord").build();
		pretty.print(outputFileName + ".html");
	}
}