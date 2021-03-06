package ru.misis.asu.nlp.npr;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ru.misis.asu.nlp.commons.algo.TemplateElement;
import ru.misis.asu.nlp.commons.exceptions.ProcessingException;
import ru.misis.asu.nlp.commons.io.FileUtils;
import ru.misis.asu.nlp.morphoanalysis.resource.MorphDictionary;
import ru.misis.asu.nlp.npr.NPTemplateEncoder.TemplateParser.InputLine;
import ru.misis.asu.nlp.npr.NPTemplateEncoder.TemplateParser.Settings;
import ru.misis.asu.nlp.npr.NPTemplateEncoder.TemplateParser.VariableMap;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NPTemplateEncoderTest {
	//TODO Make a little morph dictionary for testing

	private static File            resFile;
	private static MorphDictionary dictionary;
	private static String templatesFileName = "templates/np.txt";

	@BeforeClass
	public static void init() {
		/*System.out.println("Starting deserialization of morph dictionary");

		String dictFileName = "../morphoanalysis/src/main/resources/data/dict.opcorpora.raw";
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
				new FileInputStream(dictFileName), 100 * 1024))) {
			dictionary = (MorphDictionary) in.readObject();
		} catch (Exception e) {
			fail("Could nor load morph dictionary in NPTemplateEncoderTest class: " + e.getMessage());
		}

		System.out.println("Deserialization of morph dictionary finished");

		URL resURL = NPTemplateEncoderTest.class.getClassLoader().getResource(templatesFileName);
		try {
			resFile = new File(resURL.toURI());
		} catch (Exception e) {
			fail("Error while reading test file: templates/np.txt ");
		}*/
	}

	@Test
	@Ignore
	public void testNPTemplateEncoder() {
		NPTemplateEncoder encoder = null;
		encoder = new NPTemplateEncoder(FileUtils.readLines(resFile), dictionary);
		Assert.assertNotEquals(encoder, null);
	}

	private abstract class ParsingTestCase {
		String      testString;
		VariableMap variables;
		String[]    validResult;
	}


	private class ParsingTestCase1 extends ParsingTestCase {
		{
			variables = new VariableMap();
			variables.put("var1", Arrays.asList(new String[]{ "value11", "value12" }));
			variables.put("var2", Arrays.asList(new String[]{ "value21" }));
			variables.put("var3", Arrays.asList(new String[]{ "value31", "value32", "value33" }));
			variables.put("var4", Arrays.asList(new String[]{ "value41", "value42" }));
			NPTemplateEncoder.TemplateParser.Settings settings = new Settings();
			String sep = settings.WS;
			testString = "NP" + settings.NAME_VALUE_SEPARATOR + "var1" + sep + "var2" + sep + "part1" + sep + "var3" +
						 settings.OR + "var4";
			validResult = new String[]{ "value11" + sep + "value21" + sep + "part1" + sep + "value31",
					"value11" + sep + "value21" + sep + "part1" + sep + "value32",
					"value11" + sep + "value21" + sep + "part1" + sep + "value33",
					"value12" + sep + "value21" + sep + "part1" + sep + "value31",
					"value12" + sep + "value21" + sep + "part1" + sep + "value32",
					"value12" + sep + "value21" + sep + "part1" + sep + "value33", "value41", "value42" };
		}
	}


	private class ParsingTestCase2 extends ParsingTestCase {
		{
			variables = new VariableMap();
			variables.put("var1", Arrays.asList(new String[]{ "value11", "value12" }));
			variables.put("var2", Arrays.asList(new String[]{ "value21" }));
			variables.put("var3", Arrays.asList(new String[]{ "value31", "value32", "value33" }));
			NPTemplateEncoder.TemplateParser.Settings settings = new Settings();
			String sep = settings.WS;
			testString = "NP" + settings.NAME_VALUE_SEPARATOR + "var1" + sep + "var2" + sep + "part1" + sep + "var3";
			validResult = new String[]{ "value11" + sep + "value21" + sep + "part1" + sep + "value31",
					"value11" + sep + "value21" + sep + "part1" + sep + "value32",
					"value11" + sep + "value21" + sep + "part1" + sep + "value33",
					"value12" + sep + "value21" + sep + "part1" + sep + "value31",
					"value12" + sep + "value21" + sep + "part1" + sep + "value32",
					"value12" + sep + "value21" + sep + "part1" + sep + "value33" };
		}
	}


	private class ParsingTestCase3 extends ParsingTestCase {
		{
			variables = new VariableMap();
			NPTemplateEncoder.TemplateParser.Settings settings = new Settings();
			String sep = settings.WS;
			testString =
					"NP" + settings.NAME_VALUE_SEPARATOR + "part1" + settings.OR + "part2" + settings.OR + "part3" +
					sep + "part4";
			validResult = new String[]{ "part1", "part2", "part3" + sep + "part4" };
		}
	}

	@Test
	@Ignore
	public void testParseValueWithOR() throws ProcessingException {
		ParsingTestCase test = new ParsingTestCase3();
		List<InputLine> list =
				new NPTemplateEncoder.TemplateParser.ORResolver().resolve(InputLine.newInstance(test.testString));
		List<String> result = new ArrayList<>();
		for (InputLine line : list) {
			result.add(line.getValue());
		}
		Assert.assertArrayEquals(result.toArray(), test.validResult);
	}

	@Test
	@Ignore
	public void testParseValueWithVariables() throws ProcessingException {
		ParsingTestCase test = new ParsingTestCase1();
		List<InputLine> list = new NPTemplateEncoder.TemplateParser.NonRecursiveResolver(test.variables)
				.resolve(InputLine.newInstance(test.testString));
		List<String> result = new ArrayList<>();
		for (InputLine line : list) {
			result.add(line.getValue());
		}
		Assert.assertArrayEquals(result.toArray(), test.validResult);

		test = new ParsingTestCase2();
		list = new NPTemplateEncoder.TemplateParser.NonRecursiveResolver(test.variables)
				.resolve(InputLine.newInstance(test.testString));
		result = new ArrayList<>();
		for (InputLine line : list) {
			result.add(line.getValue());
		}
		Assert.assertArrayEquals(result.toArray(), test.validResult);
	}

	@Test
	@Ignore
	public void testGetAutomaton() {
		// fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetAlphabet() {
		NPTemplateEncoder encoder = null;
		encoder = new NPTemplateEncoder(FileUtils.readLines(resFile), dictionary);
		TemplateElement[] alphabet = encoder.getAlphabet();
		TemplateElement[] validAlphabet = new TemplateElement[7];
		validAlphabet[0] = new NPTemplateElement(dictionary, new String[]{ "NOUN" });
		validAlphabet[2] = new NPTemplateElement(dictionary, new String[]{ "PREP" });
		validAlphabet[3] = new NPTemplateElement(dictionary, new String[]{ "VERB" });
		for (TemplateElement resulElement : alphabet) {
			Assert.assertTrue(Arrays.asList(validAlphabet).contains(resulElement));
		}
	}

	@Test
	@Ignore
	public void testGetTemplates() {
		// fail("Not yet implemented");
	}

}
