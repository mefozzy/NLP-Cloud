package ru.misis.asu.nlp.commons.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.uima.jcas.tcas.Annotation;

public class PrettyPrinter {
	private static final String newline = System.getProperty("line.separator");

	private static final String HEADER;
	private static final String FOOTER;

	static {
		StringBuilder header = new StringBuilder();
		header.append("<html>").append(newline);
		header.append("<head>").append(newline);
		header.append("<title>").append(newline);
		header.append("Test result").append(newline);
		header.append("</title>").append(newline);
		header.append("</head>").append(newline);
		header.append("<body>").append(newline);
		HEADER = header.toString();

		StringBuilder footer = new StringBuilder();
		header.append("</body>").append(newline);
		header.append("</html>").append(newline);
		FOOTER = footer.toString();
	}

	private String text;
	private Iterator<? extends Annotation>[] iterators;
	private String[] typeNames;

	@SafeVarargs
	private PrettyPrinter(String text,
			Iterator<? extends Annotation>... iterators) {
		this.text = text;
		this.iterators = iterators;
	}

	private String formatString(Annotation annotation, String typeName,
			String prefix) {
		StringBuilder result = new StringBuilder();
		int aBeg = annotation.getBegin();
		int aEnd = annotation.getEnd();
		int start = Math.max(0, aBeg - 30);
		int end = Math.min(text.length(), aEnd + 30);
		result.append("<font size=\"5\"><b><font size=\"9\">").append(prefix)
				.append(typeName).append(":&nbsp&nbsp&nbsp&nbsp</b></font>");
		result.append(text.substring(start, aBeg)).append(
				"<font size=\"9\"><b>");
		result.append(text.substring(aBeg, aEnd)).append("</b></font>");
		result.append(text.substring(aEnd, end)).append("</font><br><br>");
		return result.toString();
	}

	private String getSectionSeparator() {
		return "<br><br>-----------------------------------<br><br>";
	}

	public void print(String fileName) {
		try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName))) {
			out.write(HEADER);
			for (int i = 0; i < iterators.length; i++) {
				Iterator<? extends Annotation> iterator = iterators[i];
				int l = 0;
				while (iterator.hasNext()) {
					Annotation annotation = iterator.next();
					out.write(formatString(annotation, typeNames[i], "#"
							+ String.valueOf(++l) + "&nbsp"));
				}
				out.write(getSectionSeparator());
			}
			out.write(FOOTER);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static class Builder {
		private PrettyPrinter instance;

		@SafeVarargs
		public Builder(String text, Iterator<? extends Annotation>... iterators) {
			instance = new PrettyPrinter(text, iterators);
		}

		public Builder typeNames(String... typeNames) {
			instance.typeNames = typeNames;
			return this;
		}

		public PrettyPrinter build() {
			if (instance.typeNames == null) {
				instance.typeNames = new String[instance.iterators.length];
				Arrays.fill(instance.typeNames, "Annotation");
			}
			return instance;
		}
	}
}
