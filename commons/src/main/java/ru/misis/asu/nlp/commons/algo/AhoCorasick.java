package ru.misis.asu.nlp.commons.algo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


public class AhoCorasick {
	public class Node {
		private class EdgeList {
			private Map<TemplateElement, Node>	nodes;

			public EdgeList() {
				nodes = new HashMap<>();
			}

			public TemplateElement[] keys() {
				Set<TemplateElement> keys = nodes.keySet();
				TemplateElement[] result = new TemplateElement[keys.size()];
				result = keys.toArray(result);
				return result;
			}

			public Node get(final TemplateElement te) {
				return nodes.get(te);
			}

			public void put(final TemplateElement te, final Node node) {
				nodes.put(te, node);
			}

			@Override
			public String toString() {
				return Arrays.toString(nodes.entrySet().toArray());
			}
		}

		private EdgeList		edgeList;
		private Node			fail;
		private Set<Template>	outputs;

		private Node() {
			edgeList = new EdgeList();
			fail = null;
			outputs = new HashSet<>();
		}

		private Node extend(final TemplateElement b) {
			if (edgeList.get(b) != null) {
				return edgeList.get(b);
			}
			Node nextState = new Node();
			edgeList.put(b, nextState);
			return nextState;
		}

		private Node extendAll(final TemplateElement[] bytes) {
			Node state = this;
			for (TemplateElement b: bytes) {
				if (state.edgeList.get(b) != null) {
					state = state.edgeList.get(b);
				} else {
					state = state.extend(b);
				}
			}
			return state;
		}

		private Node get(final TemplateElement b) {
			return edgeList.get(b);
		}

		private void put(final TemplateElement b, final Node s) {
			edgeList.put(b, s);
		}

		private TemplateElement[] keys() {
			return edgeList.keys();
		}

		private Node getFail() {
			return fail;
		}

		private void setFail(final Node f) {
			fail = f;
		}

		private void addOutput(final Template t) {
			outputs.add(t);
		}

		public Set<Template> getOutputs() {
			return outputs;
		}

		@Override
		public String toString() {
			return "outputs: " + Arrays.toString(outputs.toArray());
		}
	}

	private TemplateElement[]	elements;
	private Node				root;

	public AhoCorasick(final Template[] templates, final TemplateElement[] alphabet) {
		elements = alphabet;
		root = new Node();
		for (Template t: templates) {
			add(t);
		}
		prepareFailTransitions();
	}

	private void add(final Template template) {
		Node lastState = root.extendAll(template.getElements());
		lastState.addOutput(template);
	}

	private void prepareFailTransitions() {
		LinkedList<Node> q = new LinkedList<>();
		for (int i = 0; i < elements.length; i++) {
			if (root.get(elements[i]) != null) {
				root.get(elements[i]).setFail(root);
				q.push(root.get(elements[i]));
			}
		}
		prepareRoot();
		while (!q.isEmpty()) {
			Node state = q.pop();
			TemplateElement[] keys = state.keys();
			for (TemplateElement a: keys) {
				Node r = state;
				Node s = r.get(a);
				q.push(s);
				r = r.getFail();
				while (r.get(a) == null) {
					r = r.getFail();
				}
				s.setFail(r.get(a));
				s.getOutputs().addAll(r.get(a).getOutputs());
			}
		}
	}

	private void prepareRoot() {
		for (int i = 0; i < elements.length; i++) {
			if (root.get(elements[i]) == null) {
				root.put(elements[i], root);
			}
		}
	}

	public Node getRoot() {
		return root;
	}

	public Node getNext(final Node prevState, final TemplateElement el) {
		Node state = prevState;

		while (state.get(el) == null) {
			state = state.getFail();
		}
		state = state.get(el);
		return state;
	}
}