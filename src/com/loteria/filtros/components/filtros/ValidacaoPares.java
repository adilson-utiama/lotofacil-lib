package com.loteria.filtros.components.filtros;

import com.loteria.model.Jogo;

public class ValidacaoPares implements FiltroLoteria {
	
	private Integer minimo;
	private Integer maximo;
	
	public ValidacaoPares(Integer minimo, Integer maximo) {
		this.minimo = minimo;
		this.maximo = maximo;
	}

	@Override
	public boolean valida(Jogo jogo) {
		return jogo.getPares() >= this.minimo && jogo.getPares() <= maximo;

	}

	
}
