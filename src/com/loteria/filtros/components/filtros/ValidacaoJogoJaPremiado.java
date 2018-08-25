package com.loteria.filtros.components.filtros;

import com.loteria.model.Jogo;
import com.loteria.service.ConcursosService;

public class ValidacaoJogoJaPremiado implements FiltroLoteria {
	
	private ConcursosService service;

	public ValidacaoJogoJaPremiado(ConcursosService service) {
		this.service = service;
	}

	@Override
	public boolean valida(Jogo jogo) {
		int ultimoResultado = service.ultimoResultado();
		for(int i = 1; i < ultimoResultado; i++) {
			Jogo jogoRecuperado = service.recuperaJogo(i);
			boolean repetido = jogoRecuperado.getJogo().containsAll(jogo.getJogo());
			if(repetido) {
				return false;
			}
		}
		return true;
	}

}
