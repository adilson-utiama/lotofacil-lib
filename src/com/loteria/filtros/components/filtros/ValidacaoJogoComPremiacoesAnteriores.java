package com.loteria.filtros.components.filtros;

import java.util.Map;

import com.loteria.model.Jogo;

public class ValidacaoJogoComPremiacoesAnteriores implements FiltroLoteria {
	
	private Integer quantidadeConcursosPremiados; 
	
	private static final int PREMIACAO_MINIMA = 11;
	
	private Map<Integer, Jogo> listaConcursos;

	public ValidacaoJogoComPremiacoesAnteriores(Map<Integer, Jogo> concursos, Integer quantidadeConcursosPremiados) {
		this.quantidadeConcursosPremiados = quantidadeConcursosPremiados;
		this.listaConcursos = concursos;
	}

	@Override
	public boolean valida(Jogo jogo) {
		return analisa(jogo);
	}

	private boolean analisa(Jogo jogo) {
		
		int jogosComAcerto = 0;
		for(Jogo jogoRecuperado : listaConcursos.values()) {
			int acertos = 0;
			for (Integer numero : jogo.getJogo()) {
				if(jogoRecuperado.getJogo().contains(numero)) {
					acertos++;
				}
			}
			if(acertos >= PREMIACAO_MINIMA) jogosComAcerto++;
			acertos = 0;
		}
		if(jogosComAcerto >= quantidadeConcursosPremiados) {
			return true;
		}
		return false;
	}

}
