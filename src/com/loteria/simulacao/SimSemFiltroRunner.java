package com.loteria.simulacao;

import java.util.HashMap;
import java.util.Map;

import com.loteria.geradores.JogoRandom;
import com.loteria.model.Jogo;
import com.loteria.util.ConferidorJogos;

public class SimSemFiltroRunner implements Runnable {

	private Jogo jogoAlvo;
	private Map<Integer, Integer> listaAcertos;
	private int dezenas;
	private int acertos;
	private int quantidadeJogos;

	public SimSemFiltroRunner(Jogo jogoAlvo, Map<Integer, Integer> listaAcertos, int dezenas, int acertos, int quantidadeJogos) {
		this.jogoAlvo = jogoAlvo;
		this.listaAcertos = listaAcertos;
		this.dezenas = dezenas;
		this.acertos = acertos;
		this.quantidadeJogos = quantidadeJogos;
	}

	@Override
	public void run() {
		
		do {
			acertos = 0;
			Jogo jogoAleatorio = JogoRandom.gerarJogoAleatorio(dezenas, 25);
			acertos = ConferidorJogos.confereJogo(jogoAlvo, jogoAleatorio);
			
			if(acertos >= 11) {
				if(listaAcertos.get(acertos) == null) {
					listaAcertos.put(acertos, 0);
				}
				listaAcertos.put(acertos, listaAcertos.get(acertos) + 1);
			}
			quantidadeJogos++;
		}while(acertos != 15);

	}

}
