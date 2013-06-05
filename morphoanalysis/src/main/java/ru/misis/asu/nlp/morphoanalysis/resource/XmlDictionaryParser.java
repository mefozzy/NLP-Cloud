package ru.misis.asu.nlp.morphoanalysis.resource;

import static java.lang.System.currentTimeMillis;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class XmlDictionaryParser {
	public static MorphDictionaryImpl parse(InputStream in) throws IOException,
			SAXException, ParserConfigurationException {
		SAXParser xmlParser = SAXParserFactory.newInstance().newSAXParser();
		XMLReader xmlReader = xmlParser.getXMLReader();

		DictionaryXmlHandler dictHandler = new DictionaryXmlHandler();
		dictHandler.addLemmaFilter(new LemmaByGrammemFilter("Surn", "Patr",
				"Orgn"));

		xmlReader.setContentHandler(dictHandler);
		InputSource xmlSource = new InputSource(in);
		System.out.println("About to parse xml dictionary file");
		long timeBefore = currentTimeMillis();
		xmlReader.parse(xmlSource);
		System.out.println("Parsing finished in "
				+ (currentTimeMillis() - timeBefore) + " ms");
		return dictHandler.getDictionary();
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err
					.println("Usage: <xml-dictionary-file> <serialized-output-path>");
			return;
		}

		File dictXmlFile = new File(args[0]);
		if (!dictXmlFile.isFile()) {
			throw new IllegalStateException(dictXmlFile + " does not exist");
		}
		File outPath = new File(args[1]);

		MorphDictionaryImpl dict = parse(new FileInputStream(dictXmlFile));

		System.out.println("Preparing to serialization...");
		long timeBefore = currentTimeMillis();
		OutputStream fout = new BufferedOutputStream(new FileOutputStream(
				outPath), 8192 * 8);
		ObjectOutputStream out = new ObjectOutputStream(fout);
		try {
			out.writeObject(dict);
		} finally {
			out.close();
		}
		System.out.println("Serialization finished in "
				+ (currentTimeMillis() - timeBefore) + " ms");
	}
}