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
			text = "������� � 1815 ���� � ������ ���������� ��������� "
					+ "������������� ���-�����, ����������� ������ ������ ������� � 1914 ����.";
			resultTokens = new String[] { "�������", "�", "1815", "����", "�",
					"������", "����������", "���������", "�������������",
					"���-�����", ",", "�����������", "������", "������",
					"�������", "�", "1914", "����" };
		}
	}

	private class SimpleAbbreviationTest extends TokenizationTestCase {
		{
			text = "�.�., �� �� �������� ������� ���������. ������, �.�., �� ����������. "
					+ "��� ������� ���������� ������, ��������� ��������� � �.�. � �.�.";
			resultTokens = new String[] { "�.�.", "�.�.", "�.�.", "�.�." };
		}
	}

	private class SimpleRussianWordTestCase extends TokenizationTestCase {
		{
			text = "� ����� ����� ��� �����";
			resultTokens = new String[] { "�", "�����", "�����", "���", "�����" };
		}
	}

	private class RussianWordTestCase extends TokenizationTestCase {
		{
			text = "���������� (Sukabumi) � ����� � ���������, �� ������� ���,"
					+ " � ��������� �������� ���. ���������� ������ �������� � ��������������� "
					+ "���������������� ������� � ������������� (����)";
			resultTokens = new String[] { "����������", "�����", "�",
					"���������", "��", "�������", "���", "�", "���������",
					"��������", "���", "����������", "������", "��������", "�",
					"���������������", "����������������", "�������",
					"�������������", "����" };
		}
	}

	private class ComplexWordTestCase extends TokenizationTestCase {
		{
			text = "����������� ��������� ������-�������������� ���. "
					+ "� ��������� �������������-������������ ���������� ��������� ����.";
			resultTokens = new String[] { "������-��������������",
					"�������������-������������" };
		}
	}
}
