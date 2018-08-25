package com.loteria.filtros.components.filtros;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.loteria.model.Jogo;

public class ValidacaoUltimoNumeroMenorJogo implements FiltroLoteria {
	
	private Integer menorNumero;

	public ValidacaoUltimoNumeroMenorJogo(Integer menorNumero) {
		this.menorNumero = menorNumero;
	}

	@Override
	public boolean valida(Jogo jogo) {
		List<Integer> listaJogo = new ArrayList<>(jogo.getJogo());
		Collections.sort(listaJogo);
		return listaJogo.get(listaJogo.size() - 1) >= menorNumero;
	}

}
