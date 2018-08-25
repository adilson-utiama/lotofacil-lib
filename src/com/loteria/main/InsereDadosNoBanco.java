package com.loteria.main;

import java.util.Map;

import com.loteria.model.Jogo;
import com.loteria.repository.ConcursosRepository;

public class InsereDadosNoBanco {

	public static void main(String[] args) {
		
		ConcursosRepository repo = new ConcursosRepository();
		repo.importarDadosParaObjeto();
		
		System.out.println(repo.getConcursosCadastrados());
		
		Map<Integer, Jogo> jogos = repo.getJogos();
		for (int i = 1; i <= jogos.size(); i++) {
			System.out.println(i + " : " +jogos.get(i));
		}
	}
}
