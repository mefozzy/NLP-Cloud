package ru.misis.asu.nlp.npr;

import ru.misis.asu.nlp.commons.algo.Template;
import ru.misis.asu.nlp.commons.algo.TemplateElement;


public class NPTemplate extends Template {
	private int	headInd			= -1;
	private int	particleInd		= -1;
	private int	prepositionInd	= -1;

	protected NPTemplate(final TemplateElement[] elements) {
		super(elements);
	}

	public int getHeadInd() {
		return headInd;
	}

	public int getParticleInd() {
		return particleInd;
	}

	public int getPrepositionInd() {
		return prepositionInd;
	}

	void setHeadInd(final int headInd) {
		this.headInd = headInd;
	}

	void setParticleInd(final int particleInd) {
		this.particleInd = particleInd;
	}

	void setPrepositionInd(final int prepositionInd) {
		this.prepositionInd = prepositionInd;
	}
}
