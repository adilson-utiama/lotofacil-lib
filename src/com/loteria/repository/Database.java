package com.loteria.repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.loteria.model.Jogo;

public class Database implements Serializable{

	private static final long serialVersionUID = 1L;

	private Map<Integer, Jogo> database = new HashMap<>();
	
	public Map<Integer, Jogo> getDatabase() {
		return database;
	}

	public void addJogo(Jogo jogo) {
		database.put(database.size() + 1, jogo);
		
	}

	public int concursosCadastrados() {
		return database.size();
	}

	public void adicionaJogo(int concurso, Jogo jogo) {
		// TODO Auto-generated method stub
		
	}

	public Jogo get(int concurso) {
		return database.get(concurso);
	}
}
