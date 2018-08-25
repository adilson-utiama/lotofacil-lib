package com.loteria.filtros.components.filtros;

import com.loteria.model.Jogo;

public class ValidacaoSomaTotal implements FiltroLoteria {
	
	private Integer minimo;
	private Integer maximo;

	public ValidacaoSomaTotal(Integer minimo, Integer maximo) {
		super();
		this.minimo = minimo;
		this.maximo = maximo;
	}

	@Override
	public boolean valida(Jogo jogo) {
		return jogo.getSoma() >= this.minimo && jogo.getSoma() <= maximo;
	}

}
