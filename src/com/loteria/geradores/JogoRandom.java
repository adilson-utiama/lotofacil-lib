package com.loteria.geradores;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.loteria.model.Jogo;

public class JogoRandom {

	public static Jogo gerarJogoAleatorio(int dezenas, int faixaNumeros){
		List<Integer> random = new LinkedList<>();
		for (int i = 0; i < dezenas; i++) {
			int n01 = (int) (1 + Math.random() * faixaNumeros);
			if (random.contains(n01)) {
				do {
					n01 = (int) (1 + Math.random() * faixaNumeros);
				} while (random.contains(n01));
				random.add(n01);
			} else {
				random.add(n01);
			}
		}
		Collections.sort(random);
		
		return new Jogo(random);
	}
	
	public static Jogo gerarJogoAleatorioComInclusao(int dezenas, int faixaNumeros, Set<Integer> numerosIncluir) {
		Set<Integer> lista = new HashSet<>();
		if(!numerosIncluir.isEmpty()) {
			lista.addAll(numerosIncluir);
		}
		return completarJogo(dezenas, lista);
	}
	
	public static Jogo completarJogo(int dezenas, Set<Integer> jogo){
		do{
			int numero = (int) (1 + Math.random() * 25);
			jogo.add(numero);
		}while(jogo.size() != dezenas);
		
		return new Jogo(jogo);
	}
	
	public static void main(String[] args) {
		
		Set<Integer> numeros = new  HashSet<>(Arrays.asList(1,25,15));
		Jogo jogo = JogoRandom.gerarJogoAleatorioComInclusao(18, 25, numeros);
		System.out.println(jogo.getJogo().size());
		System.out.println(jogo);
	}

}
