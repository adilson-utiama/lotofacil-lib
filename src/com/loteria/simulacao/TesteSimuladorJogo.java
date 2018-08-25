package com.loteria.simulacao;

import java.util.Map;

import com.loteria.filtros.components.AplicadorDeFiltro;
import com.loteria.filtros.components.filtros.FiltroLimitacaoSequencia;
import com.loteria.filtros.components.filtros.FiltroLinha;
import com.loteria.filtros.components.filtros.Filtros;
import com.loteria.filtros.components.filtros.ValidacaoJogoComPremiacoesAnteriores;
import com.loteria.filtros.components.filtros.ValidacaoJogoJaPremiado;
import com.loteria.filtros.components.filtros.ValidacaoPares;
import com.loteria.filtros.components.filtros.ValidacaoSomaTotal;
import com.loteria.geradores.JogoRandom;
import com.loteria.model.Jogo;
import com.loteria.service.ConcursosService;

public class TesteSimuladorJogo {

	public static void main(String[] args) {
		
		ConcursosService service = new ConcursosService();
		Map<Integer, Jogo> jogos = service.recuperaJogos(service.ultimoResultado(), 10);
		
		Filtros filtros = new Filtros();
		filtros.adiciona(new ValidacaoPares(7, 9));
		filtros.adiciona(new ValidacaoSomaTotal(175, 220));
		filtros.adiciona(new FiltroLimitacaoSequencia(4));
		filtros.adiciona(new ValidacaoJogoJaPremiado(service));
		filtros.adiciona(new ValidacaoJogoComPremiacoesAnteriores(jogos , 5));
		filtros.adiciona(new FiltroLinha());
		
		Jogo jogo;
		boolean ok;
		do {
			jogo = JogoRandom.gerarJogoAleatorio(15, 25);
			AplicadorDeFiltro filtro = new AplicadorDeFiltro(jogo, filtros);
			ok = filtro.aplicarFiltros();
		}while(!ok);
		
		System.out.println(jogo);
		System.out.println("Pares: " + jogo.getPares());
		System.out.println("Soma: " + jogo.getSoma());
		
		
		SimuladorJogo simuladorJogo = new SimuladorJogo(1675, 10, jogo);
//		Map<Integer, Integer> resultados = simuladorJogo.simula();
//		System.out.println(resultados);
		
		ResultadoSimulacao simulacao = simuladorJogo.simularJogosComFiltro(1675, 15, filtros);
		System.out.println("Simulacao com jogos COM Filtro");
		System.out.println(simulacao);
		
		simulacao = simuladorJogo.simularJogosAleatorioSemFiltro(1675, 15);
		System.out.println("Simulacao com jogos SEM Filtro");
		System.out.println(simulacao);
		
		
		
//		simulacao = simuladorJogo.simularJogosAleatorioSemFiltro(1675, 16);
//		System.out.println("Jogos com 16 numeros");
//		System.out.println(simulacao);
//		
//		simulacao = simuladorJogo.simularJogosAleatorioSemFiltro(1675, 17);
//		System.out.println("Jogos com 17 numeros");
//		System.out.println(simulacao);
//		
//		simulacao = simuladorJogo.simularJogosAleatorioSemFiltro(1675, 18);
//		System.out.println("Jogos com 18 numeros");
//		System.out.println(simulacao);

	}
}
