package ru.misis.asu.nlp.preprocessing;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.language.LanguageIdentifier;

import ru.misis.asu.nlp.commons.io.NIOReader;

public class TikaProcessor {
	private String text;
	private String language;

	public static TikaProcessor newInstance(URI fileURI) {
		return newInstance(new File(fileURI));
	}

	public static TikaProcessor newInstance(File file) {
		TikaProcessor processor = new TikaProcessor();
		processor.initialize(file);
		return processor;
	}

	private TikaConfig getTikaConfig() {
		return TikaConfig.getDefaultConfig();
	}

	public void initialize(File file) {
		Tika tika = new Tika(getTikaConfig());

		byte[] bytes = null;
		try {
			bytes = NIOReader.readBytes(file);
		} catch (Exception e) {
			throw new RuntimeException("IOException: " + e.getMessage(), e);
		}

		try (InputStream in = new BufferedInputStream(new ByteArrayInputStream(
				bytes))) {
			text = tika.parseToString(in);
			LanguageIdentifier langIndent = new LanguageIdentifier(text);
			language = langIndent.getLanguage();
		} catch (IOException e) {
			throw new RuntimeException("IOException: " + e.getMessage(), e);
		} catch (TikaException e) {
			throw new RuntimeException("TikaException: " + e.getMessage(), e);
		}
	}

	public String getText() {
		return text;
	}

	public String getLanguage() {
		return language;
	}
}
