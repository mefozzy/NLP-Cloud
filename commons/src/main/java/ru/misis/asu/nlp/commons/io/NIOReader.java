package ru.misis.asu.nlp.commons.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOReader {
	private NIOReader() {
		throw new UnsupportedOperationException();
	}
	
	public static byte[] readBytes(URI fileURI) {
		return readBytes(new File(fileURI));
	}
	
	public static byte[] readBytes(String fileName) {
		return readBytes(new File(fileName));
	}
	
	public static byte[] readBytes(File file) {
		try (FileInputStream inputStream = new FileInputStream(file);
			 FileChannel in = inputStream.getChannel()) {
			if (in.size() > Integer.MAX_VALUE) {
				throw new RuntimeException();
			}
			
			ByteBuffer buff = ByteBuffer.allocate((int) in.size());
			in.read(buff);
			return buff.array();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
