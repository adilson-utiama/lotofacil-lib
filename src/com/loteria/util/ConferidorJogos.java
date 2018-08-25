package com.loteria.util;

import java.util.HashMap;
import java.util.Map;

import com.loteria.geradores.JogoRandom;
import com.loteria.model.Jogo;
import com.loteria.service.ConcursosService;

public class ConferidorJogos {
	
	public static Integer confereJogo(Jogo concursoSorteado, Jogo jogoAConferir) {
		int acertos = 0;
		for(Integer numero : jogoAConferir.getJogo()) {
			if(concursoSorteado.getJogo().contains(numero)) acertos++;
		}
		return acertos;
	}

	public static Map<Integer, Integer> acertosEmConcursos(Jogo jogo, ConcursosService service, Integer quantidadeConcursos){
		HashMap<Integer, Integer> resultados = new HashMap<Integer, Integer>();
		Integer ultimoResultado = service.ultimoResultado();
		Integer concursosAnalisar = ultimoResultado - quantidadeConcursos;
		
		for(int index = ultimoResultado; index > concursosAnalisar; index--) {
			int contador = 0;
			Jogo jogoRecuperado = service.recuperaJogo(index);
			contador += confereJogo(jogoRecuperado, jogo);
			resultados.put(index, contador);
			contador = 0;
		}
		return resultados;
	}
	
	public static Map<Integer, Integer> acertosEmConcursos(Map<Integer, Jogo> concursos, Jogo jogo){
		HashMap<Integer, Integer> resultados = new HashMap<Integer, Integer>();
		concursos.forEach((concursoNumero, jogoNumeros) -> {
			int contador = 0;
			contador += confereJogo(jogoNumeros, jogo);
			resultados.put(concursoNumero, contador);
			contador = 0;
		});
		return resultados;
	}
	
	public static Map<Integer, Integer> acertosEmTodosConcursos(Jogo jogo, ConcursosService service){
		HashMap<Integer, Integer> resultados = new HashMap<Integer, Integer>();
		int acertos = 0;
		for(int index = 1; index <= service.ultimoResultado(); index++) {
			acertos = confereJogo(service.recuperaJogo(index), jogo);
			if(acertos >= 11) {
				if(resultados.get(acertos) == null) {
					resultados.put(acertos, 0);
				}
				resultados.put(acertos, resultados.get(acertos) + 1);
			}
			acertos = 0;
		}
		
		return resultados;
	}
	
	public static void main(String[] args) {
		
		Jogo jogo = JogoRandom.gerarJogoAleatorio(15, 25);
		Map<Integer, Integer> resultados = acertosEmTodosConcursos(jogo, new ConcursosService());
		System.out.println(resultados);
	}
}
