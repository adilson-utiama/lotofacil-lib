package com.loteria.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.loteria.model.Jogo;
import com.loteria.service.ConcursosService;

public class ConcursosUtil {

	private ConcursosService service;
	
	public ConcursosUtil(ConcursosService service) {
		this.service = service;
	}
	
	public Map<Integer, Jogo> concursosSelecionados(Integer concursoAlvo, Integer quantidade){
		Map<Integer, Jogo> concursos = new HashMap<>();
		int faixaConcursos = concursoAlvo - quantidade;
		for(int index = concursoAlvo; index > faixaConcursos; index-- ) {
			Jogo jogo = service.recuperaJogo(index);
			concursos.put(index, jogo);
		}
		
		return concursos;
	}
	
	public List<Jogo> selecionarUltimosConcursos(int quantidade){
		//		List<Jogo> jogos = new ArrayList<>();
//		int ultimoResultado = service.ultimoResultado();
//		int faixaConcursos = ultimoResultado - quantidade;
//		for(int index = faixaConcursos + 1; index <= ultimoResultado; index++) {
//			Jogo jogo = service.recuperaJogo(index);
//			jogos.add(jogo);
//		}
		int concursoAlvo = service.ultimoResultado();
		List<Jogo> jogos = selecionarConcursos(concursoAlvo, quantidade);
		return jogos;
	}
	
	public List<Jogo> selecionarConcursos(int concursoAlvo, int quantidade){
		List<Jogo> jogos = new ArrayList<>();
		int faixaConcursos = concursoAlvo - quantidade;
		for(int index = faixaConcursos + 1; index <= concursoAlvo; index++) {
			Jogo jogo = service.recuperaJogo(index);
			jogos.add(jogo);
		}
		return jogos;
	}
	
	public static void main(String[] args) {
		
		List<Jogo> ultimosConcursos = new ConcursosUtil(new ConcursosService()).selecionarUltimosConcursos(10);
		ultimosConcursos.forEach(jogo -> System.out.println(jogo));
		System.out.println(ultimosConcursos.size());
	}

}
