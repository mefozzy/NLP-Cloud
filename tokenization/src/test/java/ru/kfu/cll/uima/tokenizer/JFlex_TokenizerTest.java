package ru.kfu.cll.uima.tokenizer;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class JFlex_TokenizerTest {
	@BeforeClass
	public static void setUpBeforeClass(){
	}

	@AfterClass
	public static void tearDownAfterClass() {
	}

	@Test
	public void testSimpleAbbreviation() {
	}

	private abstract class TokenizationTestCase {
		protected String text;
		protected String[] resultTokens;

		public String getText() {
			return text;
		}

		public String[] getResultTokens() {
			return resultTokens;
		}
	}

	private class TokenTestCase extends TokenizationTestCase {
		{
			text = "Основан в 1815 году в период британской оккупации "
					+ "Нидерландской Ост-Индии, официальный статус города получил в 1914 году.";
			resultTokens = new String[] { "Основан", "в", "1815", "году", "в",
					"период", "британской", "оккупации", "Нидерландской",
					"Ост-Индии", ",", "официальный", "статус", "города",
					"получил", "в", "1914", "году" };
		}
	}

	private class SimpleAbbreviationTest extends TokenizationTestCase {
		{
			text = "Т.е., он не выполнил условия контракта. Сделка, т.о., не состоялась. "
					+ "Ему придётся возместить убытки, заплатить неустойку и т.д. и т.п.";
			resultTokens = new String[] { "Т.е.", "т.о.", "т.д.", "т.п." };
		}
	}

	private class SimpleRussianWordTestCase extends TokenizationTestCase {
		{
			text = "Я вновь вхожу под своды";
			resultTokens = new String[] { "Я", "вновь", "вхожу", "под", "своды" };
		}
	}

	private class RussianWordTestCase extends TokenizationTestCase {
		{
			text = "Сукабукуми (Sukabumi) — город в Индонезии, на острове Ява,"
					+ " в провинции Западная Ява. Территория города выделена в самостоятельную "
					+ "административную единицу — муниципалитет (кота)";
			resultTokens = new String[] { "Сукабукуми", "город", "в",
					"Индонезии", "на", "острове", "Ява", "в", "провинции",
					"Западная", "Ява", "Территория", "города", "выделена", "в",
					"самостоятельную", "административную", "единицу",
					"муниципалитет", "кота" };
		}
	}

	private class ComplexWordTestCase extends TokenizationTestCase {
		{
			text = "Полицейские применили нервно-паралитический газ. "
					+ "В отношении демонстрантов-провокаторов возбуждено уголовное дело.";
			resultTokens = new String[] { "нервно-паралитический",
					"демонстрантов-провокаторов" };
		}
	}
}
