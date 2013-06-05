package ru.misis.asu.nlp.aggregate.utils;

import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.XMLInputSource;
import ru.misis.asu.nlp.commons.io.NIOReader;

import java.io.*;

import static java.lang.System.currentTimeMillis;

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
		if (args[0].substring(args[0].length() - 4, args[0].length()).equals(".xmi")) {
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
	}
}