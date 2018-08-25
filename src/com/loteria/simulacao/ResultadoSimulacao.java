package com.loteria.simulacao;

import java.util.Map;

import com.loteria.util.FormatadorValor;

public class ResultadoSimulacao {

	
	private int quantidadeJogos;
	private Map<Integer, Integer> resultados;

	public ResultadoSimulacao(int quantidadeJogos, Map<Integer, Integer> resultados) {
		this.quantidadeJogos = quantidadeJogos;
		this.resultados = resultados;
			
	}
	
	public int getQuantidadeJogos() {
		return quantidadeJogos;
	}
	
	public Map<Integer, Integer> getResultados() {
		return resultados;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Quantidade de Jogos: " + FormatadorValor.formataValorInteiro(getQuantidadeJogos()));
		builder.append(" - Resultados: ");
		getResultados().forEach((key, value) -> {
			builder.append("[" + key + " : " + FormatadorValor.formataValorInteiro(value) + "]");
		});
		return builder.toString();
	}
}
