package com.loteria.filtros.components.filtros;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.loteria.filtros.FiltroSequenciaDosNumeros;
import com.loteria.model.Jogo;

public class FiltroLimitacaoSequencia implements FiltroLoteria {

	private FiltroSequenciaDosNumeros filtro;
	private Integer limiteSequencia;
	
	public FiltroLimitacaoSequencia(Integer limiteSequencia) {
		this.limiteSequencia = limiteSequencia;
	}
	
	@Override
	public boolean valida(Jogo jogo) {
		this.filtro = new FiltroSequenciaDosNumeros(jogo);
		Map<Integer, List<Set<Integer>>> sequencias = filtro.getSequencias();
		
		//System.out.println(sequencias);
		
		Set<Integer> keySet = sequencias.keySet();
		for(Integer key : keySet) {
			if(key > limiteSequencia) return false;
		}
		
		return true;
	}

}
