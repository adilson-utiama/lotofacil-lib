package com.loteria.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.loteria.model.Jogo;
import com.loteria.repository.ConcursosRepository;

public class ConcursosService {

	private ConcursosRepository repo;
	
	public ConcursosService(){
		this.repo = ConcursosRepository.getDatabase();
	}
	
	public void atualizaResultados(){
		repo.atualizar();
	}
	
	public void adicionaJogo(Jogo jogo){
		repo.adicionaJogo(jogo);
	}
	
	public Jogo recuperaJogo(int concurso){
		return repo.getJogo(concurso);
	}
	
	public Map<Integer, Jogo> recuperaJogos(int concurso, int quantidade){
		Map<Integer, Jogo> jogos = new HashMap<>();
		for(int i = concurso; i > concurso - quantidade; i--){
			jogos.put(i, repo.getJogo(i));
		}
		return Collections.unmodifiableMap(jogos);
	}

	public int ultimoResultado() {
		return repo.getConcursosCadastrados();
	}
}
