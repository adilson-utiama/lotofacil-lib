package com.loteria.estatistica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.loteria.model.Jogo;
import com.loteria.service.ConcursosService;
import com.loteria.util.ConcursosUtil;
import com.loteria.util.FormatadorTexto;

public class LoteriaEstatistica {
	
	private ConcursosService service;
	
	public LoteriaEstatistica(){
		service = new ConcursosService();
	}
	
	
	public Map<Integer, Integer> frequenciaNumerosEm(int concursoAlvo, int quantidadeJogos){
		Map<Integer, Integer> frequencia = new HashMap<>();
		for(int numero = 1; numero <= 25; numero++ ) {
			frequencia.put(numero, 0);
		}
		List<Jogo> ultimosConcursos = new ConcursosUtil(service).selecionarConcursos(concursoAlvo, quantidadeJogos);
		
		for(Jogo jogo : ultimosConcursos) {
			jogo.getJogo().forEach(numero -> {
				if(frequencia.containsKey(numero)) {
					frequencia.put(numero, frequencia.get(numero) + 1);
				}
			});
		}
		
		return frequencia;
	}
	
	public Map<Integer, List<Integer>> numerosMaisSorteadosEm(int concursoAlvo, int quantidadeConcursos){
		Map<Integer, List<Integer>> maisSairam = new HashMap<>();
		for(int valor = 10; valor >= 0; valor--) {
			maisSairam.put(valor, new ArrayList<Integer>());
		}
		Map<Integer, Integer> frequencia = frequenciaNumerosEm(concursoAlvo, quantidadeConcursos);
		frequencia.forEach((key, value) -> {
			List<Integer> lista = maisSairam.get(value);
			lista.add(key);
			maisSairam.put(value, lista);
		});
		return maisSairam;
	}
	
	public List<Integer> numerosNaoSorteadosEm(int concursoAlvo) {
		List<Integer> naoSairam = new ArrayList<>();
		Set<Integer> jogo = service.recuperaJogo(concursoAlvo).getJogo();
		for(int numero = 1; numero < 25; numero++) {
			if(!jogo.contains(numero)){
				naoSairam.add(numero);
			}
		}
		return naoSairam;
	}
	
	public Map<Integer, List<Integer>> frequenciaDosNaoSorteadosEm(int concursoAlvo, int quantidadeConcursos){
		Map<Integer, List<Integer>> frequencia = new HashMap<>();
		for(int i = 0; i <= 10; i++) {
			frequencia.put(i, new ArrayList<>());
		}
		List<Integer> naoSorteados = numerosNaoSorteadosEm(concursoAlvo);
		int concursoAnterior = concursoAlvo - 1;
		Map<Integer, List<Integer>> maisSorteados = numerosMaisSorteadosEm(concursoAnterior, quantidadeConcursos);
		maisSorteados.forEach((key, value) -> {
			List<Integer> numerosSorteados = maisSorteados.get(key);
			naoSorteados.forEach(numero -> {
				if(numerosSorteados.contains(numero)) {
					List<Integer> list = frequencia.get(key);
					list.add(numero);
					frequencia.put(key, list);
				}
			});
		});
		return frequencia;
	}
	
	public List<Repetidos> repetidosEm(int concursos, int concursoAlvo){
		List<Repetidos> listaRepetidos = new ArrayList<>();
		int faixaConcursos = concursoAlvo - concursos;
		for(int concurso = concursoAlvo; concurso > faixaConcursos; concurso--) {
			int concursoAnterior = concurso - 1;
			Set<Integer> jogoAtual = service.recuperaJogo(concurso).getJogo();
			Set<Integer> jogoAnterior = service.recuperaJogo(concursoAnterior ).getJogo();
			List<Integer> numerosRepetidos = retornaNumerosRepetidos(jogoAtual, jogoAnterior);
			Repetidos repetidos = new Repetidos();
			repetidos.setConcursoPosterior(concurso);
			repetidos.setConcursoAnterior(concursoAnterior);
			repetidos.setNumerosRepetidos(numerosRepetidos);
			repetidos.setRepeticoes(numerosRepetidos.size());
			listaRepetidos.add(repetidos);
		}
		return listaRepetidos;
	}
	
	private List<Integer> retornaNumerosRepetidos(Set<Integer> jogoAtual, Set<Integer> jogoAnterior) {
		List<Integer> repetidos = new ArrayList<>();
		jogoAtual.forEach(numero -> {
			if(jogoAnterior.contains(numero)) repetidos.add(numero);
		});
		return repetidos;
	}


	public static void main(String[] args) {
		
		FormatadorTexto formatadorTexto = new FormatadorTexto();
		LoteriaEstatistica estatistica = new LoteriaEstatistica();
		int ultimoConcurso = new ConcursosService().ultimoResultado();
		
//		Map<Integer, Integer> map = estatistica.frequenciaNumerosEm(10);
//		String string = formatadorTexto.formata(map);
//		System.out.println(string);
//		
		Map<Integer, List<Integer>> maisSairam = estatistica.numerosMaisSorteadosEm(ultimoConcurso, 10);
		String string2 = formatadorTexto.formataMaisSairam(maisSairam);
		System.out.println(string2);
		
		List<Integer> naoSairam = estatistica.numerosNaoSorteadosEm(new ConcursosService().ultimoResultado());
		System.out.println(naoSairam);
		
		Map<Integer, List<Integer>> frequenciaDosNaoSorteados = estatistica.frequenciaDosNaoSorteadosEm(ultimoConcurso, 10);
		String maisSairamDosNaoSorteados = formatadorTexto.formataMaisSairam(frequenciaDosNaoSorteados);
		System.out.println(maisSairamDosNaoSorteados);
		
		List<Repetidos> listaRepetidos = estatistica.repetidosEm(10, ultimoConcurso);
		listaRepetidos.forEach(repetido -> {
			System.out.println("------------------------");
			System.out.println(repetido.getConcursoPosterior() + "-" + repetido.getConcursoAnterior() + " : " 
					+ repetido.getRepeticoes());
			System.out.println(repetido.getNumerosRepetidos());
			System.out.println("------------------------");
		});
		
	}
	
}
