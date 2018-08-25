package com.loteria.testes;

import java.util.Map;

import com.loteria.geradores.JogoRandom;
import com.loteria.model.ListaJogos;

public class ListaJogosTeste {


	public static void main(String[] args) {
		ListaJogos listaJogos = new ListaJogos();
		for (int i = 0; i < 5; i++) {
			listaJogos.adicionar(JogoRandom.gerarJogoAleatorio(15, 25));
		}
		listaJogos.frequenciaDosNumerosPrint();
		Map<Integer, Integer> map = listaJogos.frequenciaDosNumeros();
		System.out.println();
		System.out.println(map);
		System.out.println();
		System.out.println("Quantidade de jogos: " + listaJogos.getQuantidadeJogos());
		
		String lista = listaJogos.mostraJogos();
		System.out.println(lista);
		System.out.println(listaJogos.frequenciaNumeroNaLista(15));
	}
}
