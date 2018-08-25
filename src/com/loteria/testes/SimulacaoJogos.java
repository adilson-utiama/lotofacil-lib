package com.loteria.testes;

import java.util.HashMap;
import java.util.Map;

import com.loteria.filtros.components.filtros.ValidacaoPares;
import com.loteria.geradores.JogoRandom;
import com.loteria.model.Jogo;
import com.loteria.service.ConcursosService;

public class SimulacaoJogos {

	private ConcursosService service = new ConcursosService();
	
	public SimulacaoJogos() {
		
	}
	

	public Map<String, String> simular(){
		Map<String, String> resultado = new HashMap<String, String>();
		Jogo jogo = JogoRandom.gerarJogoAleatorio(15, 25);
		boolean pares = new ValidacaoPares(100, 200).valida(jogo);
		
		return resultado;
	}
}
