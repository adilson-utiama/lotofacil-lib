package com.loteria.esquemas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.loteria.estatistica.LoteriaEstatistica;
import com.loteria.model.Jogo;
import com.loteria.model.ListaJogos;
import com.loteria.service.ConcursosService;
import com.loteria.util.ConferidorJogos;

public class LotofacilEsquema {

	static final int QUANTIDADE_CONCURSOS = 10;
	static final int QUANT_NUMEROS_MAIS_SAIRAM = 10;
	static final int QUANT_NUMEROS_MENOS_SAIRAM = 4;

	public static ListaJogos gerarEsquema11Jogos(int concursoAlvo) {
		ListaJogos listaJogos = new ListaJogos();
		LoteriaEstatistica estatistica = new LoteriaEstatistica();
		Set<Integer> jogoNumeros = new HashSet<>();

		// adicionar 9 numeros que mais sairam em 10 concursos
		Map<Integer, List<Integer>> maisSorteados = estatistica.numerosMaisSorteadosEm(concursoAlvo,
				QUANTIDADE_CONCURSOS);
		insereNumeros(jogoNumeros, maisSorteados, QUANT_NUMEROS_MAIS_SAIRAM, 5);

		// adicionar 5 numeros mais sairam dos numeros que nao sairam em 10 concursos
		Map<Integer, List<Integer>> naoSorteados = estatistica.frequenciaDosNaoSorteadosEm(concursoAlvo,
				QUANTIDADE_CONCURSOS);
		int limiteNumeros = QUANT_NUMEROS_MAIS_SAIRAM + QUANT_NUMEROS_MENOS_SAIRAM;
		insereNumeros(jogoNumeros, naoSorteados, limiteNumeros, 0);

//		System.out.println(jogoNumeros.size());
//		System.out.println(jogoNumeros);

		// gera os jogos completando com numeros aleatorios que nao estao no jogo
		List<Integer> naoPresentes = new ArrayList<>();
		for (int numero = 1; numero <= 25; numero++) {
			if (!jogoNumeros.contains(numero)) {
				naoPresentes.add(numero);
			}
		}
//		System.out.println(naoPresentes.size());
//		System.out.println(naoPresentes);

		naoPresentes.forEach(numero -> {
			Set<Integer> novoJogo = new HashSet<>(jogoNumeros);
			novoJogo.add(numero);
			listaJogos.adicionar(new Jogo(novoJogo));
		});

		return listaJogos;
	}

	private static void insereNumeros(Set<Integer> jogoNumeros, Map<Integer, List<Integer>> maisSorteados, int limite,
			int limitadorChave) {
		for (int chave = 10; chave > limitadorChave; chave--) {
			if (!maisSorteados.get(chave).isEmpty()) {
				List<Integer> numeros = maisSorteados.get(chave);
				numeros.forEach(numero -> {
					if (jogoNumeros.size() < limite) {
						jogoNumeros.add(numero);
					}
				});
			}
		}
	}

	public static void main(String[] args) {
		
		ConcursosService service = new ConcursosService();
		
		for(int index = 1500; index < 1678; index++) {
		
			System.out.println("Concurso: " + index);
			
			ListaJogos listaJogos = gerarEsquema11Jogos(index);
			Map<Integer, Jogo> jogos = listaJogos.getJogos();
			Jogo jogo = service.recuperaJogo(index + 1);
			jogos.forEach((numeroJogo, jogoDaLista) -> {
				Integer acertos = ConferidorJogos.confereJogo(jogo, jogoDaLista);
				if(acertos > 11) {
					System.out.println(numeroJogo + " : " + acertos);
				}
			});
		
		}
		
	}
}
