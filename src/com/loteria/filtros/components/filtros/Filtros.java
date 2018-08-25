package com.loteria.filtros.components.filtros;

import java.util.ArrayList;
import java.util.List;

public class Filtros {

	private List<FiltroLoteria> filtros;
	
	public Filtros() {
		filtros = new ArrayList<>();
		
	}
	
	public void adiciona(FiltroLoteria filtro) {
		if(verificaFiltroExistente(filtro)) {
			filtros.add(filtro);
		}
	}
	
	private boolean verificaFiltroExistente(FiltroLoteria filtro) {
		if(!filtros.isEmpty()) {
			String name = filtro.getClass().getName();
			for(FiltroLoteria filtroLista : filtros) {
				String nomeClasse = filtroLista.getClass().getName();
				if(name.equals(nomeClasse)) {
					throw new RuntimeException("Filtro ja existe na lista");
				}
			}
		}
		return true;
		
	}

	public List<FiltroLoteria> getFiltros() {
		return filtros;
	}
	
}
