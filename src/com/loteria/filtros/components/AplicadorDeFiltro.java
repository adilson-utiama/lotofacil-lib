package com.loteria.filtros.components;

import java.util.ArrayList;
import java.util.List;

import com.loteria.filtros.components.filtros.FiltroLoteria;
import com.loteria.filtros.components.filtros.Filtros;
import com.loteria.model.Jogo;

public class AplicadorDeFiltro {

	private List<FiltroLoteria> filtros = new ArrayList<>();
	private Jogo jogo;

	public AplicadorDeFiltro(Jogo jogo) {
		this.jogo = jogo;
	}
	
	public AplicadorDeFiltro(Jogo jogo, Filtros filtros) {
		this(jogo);
		this.filtros = filtros.getFiltros();
	}

	public void adiciona(FiltroLoteria filtro) {
		String name = filtro.getClass().getName();
		for(FiltroLoteria filtroLista : filtros) {
			String filtroNome = filtroLista.getClass().getName();
			if(filtroNome.equals(name)) {
				throw new RuntimeException("Filtro ja incluido na lista.");
			}
		}
		filtros.add(filtro);
	}

	public boolean aplicarFiltros() {
		if(filtros.isEmpty()) {
			return true;
		}
		for (FiltroLoteria filtro : filtros) {
			if (filtro.valida(jogo) == false)
				return false;
		}
		return true;
	}

}
