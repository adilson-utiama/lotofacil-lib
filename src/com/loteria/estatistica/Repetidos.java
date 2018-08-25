package com.loteria.estatistica;

import java.util.List;

public class Repetidos {

	private int concursoAnterior;
	private int concursoPosterior;
	private int repeticoes;
	private List<Integer> numerosRepetidos;

	public int getConcursoAnterior() {
		return concursoAnterior;
	}

	public void setConcursoAnterior(int concursoAnterior) {
		this.concursoAnterior = concursoAnterior;
	}

	public int getConcursoPosterior() {
		return concursoPosterior;
	}

	public void setConcursoPosterior(int concursoPosterior) {
		this.concursoPosterior = concursoPosterior;
	}

	public int getRepeticoes() {
		return repeticoes;
	}

	public void setRepeticoes(int repeticoes) {
		this.repeticoes = repeticoes;
	}

	public List<Integer> getNumerosRepetidos() {
		return numerosRepetidos;
	}

	public void setNumerosRepetidos(List<Integer> numerosRepetidos) {
		this.numerosRepetidos = numerosRepetidos;
	}

}
