package ru.misis.asu.nlp.tokenization;

import java.io.CharArrayReader;
import java.io.IOException;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import ru.misis.asu.nlp.tokenization.types.Token;

/**
 * @author Marsel Sidikov, KFU
 */
public class TokenizationAnnotator extends JCasAnnotator_ImplBase {
	@Override
	public void process(JCas cas) {
		String docText = cas.getDocumentText();
		char[] text = docText.toCharArray();
		CharArrayReader reader = new CharArrayReader(text);
		JFlexTokenizer scanner = new JFlexTokenizer(reader, cas);
		while (!scanner.isEof()) {
			try {
				Token token = scanner.yylex();
				if (token != null) {
					token.addToIndexes();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
