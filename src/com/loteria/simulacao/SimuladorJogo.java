package com.loteria.simulacao;

import java.util.HashMap;
import java.util.Map;

import com.loteria.filtros.components.AplicadorDeFiltro;
import com.loteria.filtros.components.filtros.Filtros;
import com.loteria.geradores.JogoRandom;
import com.loteria.model.Jogo;
import com.loteria.service.ConcursosService;
import com.loteria.util.ConferidorJogos;

public class SimuladorJogo {

	private ConcursosService service;
	private Integer concursoAlvo;
	private Jogo jogo;
	private Integer quantidadeJogos;
	
	public SimuladorJogo(Integer concursoAlvo, Integer quantidadeJogos, Jogo jogo) {
		service = new ConcursosService(); 
		this.concursoAlvo = concursoAlvo;
		this.jogo = jogo;
		this.quantidadeJogos = quantidadeJogos;
	}
	
	
	public Map<Integer, Integer> simula() {
		Map<Integer, Integer> resultados = new HashMap<>();
		int faixaConcursos = concursoAlvo - quantidadeJogos;
		int acertos = 0;
		for(int index = concursoAlvo; index > faixaConcursos ; index--) {
			Jogo jogoRecuperado = service.recuperaJogo(index);
			for(Integer numero : jogo.getJogo()) {
				if(jogoRecuperado.getJogo().contains(numero)) acertos++;
			}
			resultados.put(index, acertos);
			acertos = 0;
		}
		return resultados;
	}
	
	public ResultadoSimulacao simularJogosAleatorioSemFiltro(Integer concursoAlvo, Integer dezenas) {
		Jogo jogoAlvo = service.recuperaJogo(concursoAlvo);
		Map<Integer, Integer> listaAcertos = new HashMap<>();
		int acertos = 0;	
		int quantidadeJogos = 0;
		
		//new Thread(new SimSemFiltroRunner(jogoAlvo, listaAcertos, dezenas, acertos, quantidadeJogos)).start();
		do {
			acertos = 0;
			Jogo jogoAleatorio = JogoRandom.gerarJogoAleatorio(dezenas, 25);
			acertos = ConferidorJogos.confereJogo(jogoAlvo, jogoAleatorio);
			
			if(acertos >= 11) {
				if(listaAcertos.get(acertos) == null) {
					listaAcertos.put(acertos, 0);
				}
				listaAcertos.put(acertos, listaAcertos.get(acertos) + 1);
			}
			quantidadeJogos++;
		}while(acertos != 15);
		
		return new ResultadoSimulacao(quantidadeJogos, listaAcertos);
	}
	
	public ResultadoSimulacao simularJogosComFiltro(Integer concursoAlvo, Integer dezenas, Filtros filtros) {
		Jogo jogoAlvo = service.recuperaJogo(concursoAlvo);
		Map<Integer, Integer> listaAcertos = new HashMap<>();
		int acertos = 0;	
		int quantidadeJogos = 0;
		
		//new Thread(new SimSemFiltroRunner(jogoAlvo, listaAcertos, dezenas, acertos, quantidadeJogos)).start();
		
		long inicio = System.currentTimeMillis();
		
		do {
			acertos = 0;
			
			Jogo jogoFiltro;
			boolean ok;
			do {
				jogoFiltro = JogoRandom.gerarJogoAleatorio(dezenas, 25);
				AplicadorDeFiltro filtro = new AplicadorDeFiltro(jogoFiltro, filtros);
				ok = filtro.aplicarFiltros();
			}while(!ok);
			acertos = ConferidorJogos.confereJogo(jogoAlvo, jogoFiltro);
			//System.out.println(acertos);
			if(acertos >= 11) {
				if(listaAcertos.get(acertos) == null) {
					listaAcertos.put(acertos, 0);
				}
				listaAcertos.put(acertos, listaAcertos.get(acertos) + 1);
			}
			quantidadeJogos++;
			if(tempoEsgotado(inicio)) {
				System.out.println("TIMEOUT!!!!");
				break;
			}
		}while(acertos != 15);
		
		return new ResultadoSimulacao(quantidadeJogos, listaAcertos);
	}


	private boolean tempoEsgotado(long inicio) {
		long tempoLimite = 360000;
		long tempoAtual = System.currentTimeMillis();
		long tempoPassado = tempoAtual - inicio;
		if(tempoPassado > tempoLimite) {
			return true;
		}
		
		return false;
	}




	
	
	
	
}
