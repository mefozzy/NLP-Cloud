package ru.misis.asu.nlp.commons.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.misis.asu.nlp.commons.exceptions.ExceptionHandler;

public class FileUtils {
	private FileUtils() {
		throw new UnsupportedOperationException();
	}

	public static List<String> readLines(File file) {
		List<String> result = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = in.readLine()) != null) {
				result.add(line);
			}
		} catch (IOException e) {
			ExceptionHandler.Rethrow(e);
		}

		return result;
	}
}
