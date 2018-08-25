package com.loteria.filtros.components.filtros;

import java.util.Set;

import com.loteria.model.Jogo;

public class FiltroLinha implements FiltroLoteria {

	@Override
	public boolean valida(Jogo jogo) {
		int[] linhas = new int[5];
		Set<Integer> jogo2 = jogo.getJogo();
		for(Integer numero : jogo2) {
			if(numero >= 1 && numero <= 5) {
				linhas[0] = linhas[0] + 1;
			} else if(numero >= 6 && numero <= 10) {
				linhas[1] = linhas[1] + 1;
			} else if(numero >= 11 && numero <= 15) {
				linhas[2] = linhas[2] + 1;
			} else if(numero >= 16 && numero <= 20) {
				linhas[3] = linhas[3] + 1;
			} else {
				linhas[4] = linhas[4] + 1;
			}
		}
//		for(int n : linhas) {
//			System.out.print(n + " ");
//		}
//		System.out.println();
		return true;
	}

}
