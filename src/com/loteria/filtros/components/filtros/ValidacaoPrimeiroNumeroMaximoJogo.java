package com.loteria.filtros.components.filtros;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.loteria.model.Jogo;

public class ValidacaoPrimeiroNumeroMaximoJogo implements FiltroLoteria {
	
	private Integer maiorNumero;
	
	public ValidacaoPrimeiroNumeroMaximoJogo(Integer maiorNumero) {
		this.maiorNumero = maiorNumero;
	}

	@Override
	public boolean valida(Jogo jogo) {
		List<Integer> listaJogo = new ArrayList<>(jogo.getJogo());
		Collections.sort(listaJogo);
		return listaJogo.get(0) <= maiorNumero;
	}

}
