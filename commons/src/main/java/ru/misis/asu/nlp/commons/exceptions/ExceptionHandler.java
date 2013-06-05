package ru.misis.asu.nlp.commons.exceptions;

import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;

public class ExceptionHandler {
	private ExceptionHandler() {
		throw new UnsupportedOperationException();
	}
	
	public static void Log(Logger logger, Exception e) {
		logger.log(Level.SEVERE, e.getMessage());
	}
	
	public static void Rethrow(Exception e) {
		throw new RuntimeException(e);
	}
	
	public static void Rethrow(String message, Exception e) {
		throw new RuntimeException(message, e);
	}
	
	public static void LogAndThrow(Logger logger, String message) {
		RuntimeException exception = new RuntimeException(message); 
		Log(logger, exception);
		throw exception;
	}
	
	public static void LogAndRethrow(Logger logger, Exception e) {
		Log(logger, e);
		Rethrow(e);
	}
	
	public static void LogAndRethrow(Logger logger, String rethrowMessage, Exception e) {
		Log(logger, e);
		Rethrow(rethrowMessage, e);
	}
}
