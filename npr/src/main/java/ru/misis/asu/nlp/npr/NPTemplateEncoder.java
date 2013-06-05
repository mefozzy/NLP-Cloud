package ru.misis.asu.nlp.npr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.google.common.base.Strings;

import ru.misis.asu.nlp.commons.algo.AhoCorasick;
import ru.misis.asu.nlp.commons.algo.Template;
import ru.misis.asu.nlp.commons.algo.TemplateElement;
import ru.misis.asu.nlp.commons.exceptions.ProcessingException;
import ru.misis.asu.nlp.morphoanalysis.resource.MorphDictionary;
import ru.misis.asu.nlp.npr.NPTemplateEncoder.TemplateParser.InputLine;
import ru.misis.asu.nlp.npr.NPTemplateEncoder.TemplateParser.Resolver;
import ru.misis.asu.nlp.npr.NPTemplateEncoder.TemplateParser.VariableMap;

public class NPTemplateEncoder {
	static class TemplateParser {
		public static class Settings {
			public final int RECURSION_DEPTH = 10;
			public final String GS = ",";
			public final String WS = ";";
			public final String OR = "or";
			public final String NAME_VALUE_SEPARATOR = "=";
			public final String TERMINAL_NAME = "NP";

			public final String PARTICLE_LABEL;
			public final String PREPOSITION_LABEL;
			public final String HEAD_LABEL;
			private Set<String> labels;
			{
				labels = new HashSet<>();
				PARTICLE_LABEL = "(PART)";
				labels.add(PARTICLE_LABEL);

				PREPOSITION_LABEL = "(PREP)";
				labels.add(PREPOSITION_LABEL);

				HEAD_LABEL = "(HEAD)";
				labels.add(HEAD_LABEL);
			}

			public boolean isLabel(final String str) {
				return labels.contains(str);
			}

			public Set<String> getLabels() {
				return labels;
			}

			public String getLabel(final String str) {
				for (String label : getLabels()) {
					if (str.startsWith(label)) {
						return label;
					}
				}
				return Strings.nullToEmpty(null);
			}
		}

		public static class InputLine {
			private String name;
			private String value;

			public InputLine(final String name, final String value) {
				this.name = name.trim();
				this.value = value.trim();
			}

			public static InputLine newInstance(final String line)
					throws ProcessingException {
				if (line == null) {
					throw new ProcessingException();
				}
				String[] parts = line.split(settings.NAME_VALUE_SEPARATOR);
				if ((parts.length != 2) || (parts[0].length() == 0)
						|| (parts[1].length() == 0)) {
					throw new ProcessingException();
				}

				return new InputLine(parts[0], parts[1]);
			}

			public String getName() {
				return name;
			}

			public String getValue() {
				return value;
			}
		}

		public static class VariableMap extends HashMap<String, List<String>> {
			private static final long serialVersionUID = -4242362114723117538L;

			public void put(final InputLine line) {
				if (containsKey(line.getName())
						&& !get(line.getName())
								.contains(line.getValue().trim())) {
					get(line.getName()).add(line.getValue().trim());
				} else {
					List<String> temp = new ArrayList<>();
					temp.add(line.getValue().trim());
					put(line.getName().trim(), temp);
				}
			}
		}

		public static abstract class Resolver {
			private Resolver resolver;

			protected Resolver(final Resolver resolver) {
				this.resolver = resolver;
			}

			public List<InputLine> resolve(final InputLine line)
					throws ProcessingException {
				if (resolver != null) {
					List<InputLine> result = new ArrayList<>();
					for (InputLine inputLine : resolver.resolve(line)) {
						result.addAll(resolveImpl(inputLine));
					}
					return result;
				}
				return resolveImpl(line);
			}

			protected abstract List<InputLine> resolveImpl(InputLine line)
					throws ProcessingException;
		}

		public static final class ORResolver extends Resolver {
			public ORResolver() {
				super(null);
			}

			@Override
			public List<InputLine> resolveImpl(final InputLine line) {
				String[] parts = line.value.split(settings.OR);
				int n = parts.length;
				List<InputLine> result = new ArrayList<>();
				for (int i = 0; i < n; i++) {
					result.add(new InputLine(line.name, parts[i].trim()));
				}
				return result;
			}
		}

		public static abstract class VariableResolver extends Resolver {
			protected VariableMap variables;

			public VariableResolver(final VariableMap variables,
					final Resolver resolver) {
				super(resolver);
				this.variables = variables;
			}

			protected static String buildFromParts(final String[] parts) {
				StringBuilder result = new StringBuilder();
				for (int i = 0; i < parts.length; i++) {
					result.append(parts[i].trim());
					if (i < (parts.length - 1)) {
						result.append(settings.WS);
					}
				}
				return result.toString();
			}
		}

		public static class NonRecursiveResolver extends VariableResolver {
			public NonRecursiveResolver(final VariableMap variables) {
				super(variables, new ORResolver());
			}

			@Override
			public List<InputLine> resolveImpl(final InputLine line)
					throws ProcessingException {
				List<InputLine> result = new ArrayList<>();
				String[] parts = line.value.split(settings.WS);
				boolean foundVariable = false;
				for (int i = 0; i < parts.length; i++) {
					String label = settings.getLabel(parts[i].trim()).trim();
					String part = parts[i].substring(label.length()).trim();
					if (variables.containsKey(part) && !part.equals(line.name)) {
						List<String> values = variables.get(part);
						for (String val : values) {
							parts[i] = label + val;
							result.addAll(resolve(new InputLine(line.name,
									buildFromParts(parts))));
						}
						foundVariable = true;
						break;
					}
				}
				if (!foundVariable) {
					InputLine newLine = new InputLine(line.name,
							buildFromParts(parts));
					result.add(newLine);
					variables.put(newLine);
				}
				return result;
			}
		}

		// Rename to multiresolver: implement multiresolving instead of
		// recursive
		public static class RecursiveResolver extends VariableResolver {
			public RecursiveResolver(final VariableMap variables) {
				super(variables, new NonRecursiveResolver(variables));
			}

			@Override
			protected List<InputLine> resolveImpl(final InputLine line)
					throws ProcessingException {
				List<InputLine> result = new ArrayList<>();
				String[] parts = line.value.split(settings.WS);
				boolean foundVariable = false;
				for (int i = 0; i < parts.length; i++) {
					String label = settings.getLabel(parts[i].trim()).trim();
					String part = parts[i].substring(label.length()).trim();
					if (variables.containsKey(part) && part.equals(line.name)) {
						List<String> values = new ArrayList<>(
								variables.get(part));
						for (String val : values) {
							for (int j = 0; j < settings.RECURSION_DEPTH; j++) {
								String[] partsArr = new String[j + 1];
								Arrays.fill(partsArr, val);
								parts[i] = label + buildFromParts(partsArr);
								result.addAll(resolve(new InputLine(line.name,
										buildFromParts(parts))));
							}
						}
						foundVariable = true;
						break;
					}
				}
				if (!foundVariable) {
					InputLine newLine = new InputLine(line.name,
							buildFromParts(parts));
					result.add(newLine);
					variables.put(newLine);
				}
				return result;
			}
		}

		private static final Settings settings = new Settings();

		private TemplateParser() {
			throw new UnsupportedOperationException();
		}

		public static Resolver getResolver(final VariableMap variables) {
			return new NonRecursiveResolver(variables);
		}

		public static Template parseTemplate(final String line,
				final MorphDictionary dict) throws ProcessingException {
			String[] elementStrings = line.split(settings.WS);
			TemplateElement[] elements = new TemplateElement[elementStrings.length];
			int heI = -1, paI = -1, prI = -1;
			Set<String> usedLabels = new HashSet<>();
			for (int i = 0; i < elements.length; i++) {
				String elementString = elementStrings[i].trim();
				String label = settings.getLabel(elementString).trim();
				if ((label.length() != 0) && usedLabels.contains(label)) {
					throw new ProcessingException("Label already used: "
							+ label);
				}
				usedLabels.add(label);
				if (label.length() != 0) {
					if (label == settings.HEAD_LABEL) {
						heI = i;
					} else if (label == settings.PARTICLE_LABEL) {
						paI = i;
					} else if (label == settings.PREPOSITION_LABEL) {
						prI = i;
					}
				}
				elements[i] = parseElement(
						elementString.substring(label.length()), dict);
			}
			if (!usedLabels.contains(settings.HEAD_LABEL)) {
				throw new ProcessingException("No (HEAD) label was found");
			}
			NPTemplate template = new NPTemplate(elements);
			template.setHeadInd(heI);
			template.setParticleInd(paI);
			template.setPrepositionInd(prI);
			return template;
		}

		private static NPTemplateElement parseElement(final String line,
				final MorphDictionary dict) {
			String[] grammems = line.split(settings.GS);
			return new NPTemplateElement(dict, grammems);
		}
	}

	private static Logger log = Logger.getLogger(NPAnnotator.class.getName());

	private Template[] templates;
	private TemplateElement[] alphabet;

	public NPTemplateEncoder(final List<String> lines,
			final MorphDictionary dict) {
		VariableMap variables = new VariableMap();
		Resolver resolver = TemplateParser.getResolver(variables);
		List<InputLine> templateStringList = new ArrayList<>();
		Set<String> usedNames = new HashSet<>();
		try {
			for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);
				InputLine iLine = InputLine.newInstance(line);
				if (usedNames.contains(iLine.getName())) {
					throw new ProcessingException("Non-terminal already used: "
							+ iLine.getName());
				}
				usedNames.add(iLine.getName());
				templateStringList.addAll(resolver.resolve(iLine));
			}

			List<Template> templatesList = new ArrayList<>();
			Set<TemplateElement> elements = new HashSet<>();
			for (InputLine line : templateStringList) {
				if (line.getName()
						.equals(TemplateParser.settings.TERMINAL_NAME)) {
					Template template = TemplateParser.parseTemplate(
							line.getValue(), dict);
					templatesList.add(template);
					Collections.addAll(elements, template.getElements());
				}
			}
			templates = new Template[templatesList.size()];
			templates = templatesList.toArray(templates);
			alphabet = new TemplateElement[elements.size()];
			alphabet = elements.toArray(alphabet);
		} catch (ProcessingException e) {
			log.severe(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public AhoCorasick getAutomaton() {
		return new AhoCorasick(templates, alphabet);
	}

	public TemplateElement[] getAlphabet() {
		return alphabet;
	}

	public Template[] getTemplates() {
		return templates;
	}
}
