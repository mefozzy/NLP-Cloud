package ru.misis.asu.nlp.commons.exceptions;

import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;

public class ExceptionHandler {
	private ExceptionHandler() {
		throw new UnsupportedOperationException();
	}
	
	public static void log(Logger logger, Exception e) {
		logger.log(Level.SEVERE, e.getMessage());
	}
	
	public static void rethrow(Exception e) {
		throw new RuntimeException(e);
	}
	
	public static void rethrow(String message, Exception e) {
		throw new RuntimeException(message, e);
	}
	
	public static void logAndThrow(Logger logger, String message) {
		RuntimeException exception = new RuntimeException(message); 
		log(logger, exception);
		throw exception;
	}
	
	public static void logAndRethrow(Logger logger, Exception e) {
		log(logger, e);
		rethrow(e);
	}
	
	public static void logAndRethrow(Logger logger, String rethrowMessage, Exception e) {
		log(logger, e);
		rethrow(rethrowMessage, e);
	}
}
