package ru.misis.asu.nlp;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.io.*;
import java.net.URL;

public class XmlReader {

	public XmlReader() {
	}

	public HashMap<String, ExtendedPreposition> load(URL filePath) {
		System.out.println("About to load: " + filePath);
		HashMap<String, ExtendedPreposition> map = new HashMap<String, ExtendedPreposition>();
		
		Properties prop = new Properties();
		try {
			prop.load(new InputStreamReader(new FileInputStream(filePath.getFile()),
					"UTF8"));
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}

		Enumeration<Object> s = prop.keys();

		while (s.hasMoreElements()) {
			String spl = (String) s.nextElement();
			String val = (String) prop.getProperty(spl);

			String[] forms = val.split(",");

			map.put(spl, new ExtendedPreposition(spl, forms));
		}
		
		System.out.println(String.format("Preposition dictionary loading finished: %d prepositions loaded.", map.size()));
		return map;
	}
}