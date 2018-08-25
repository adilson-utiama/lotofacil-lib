package com.loteria.testes;

import com.loteria.geradores.JogoRandom;
import com.loteria.model.Jogo;

public class JogoTeste {

	public static void main(String[] args) {
		
		Jogo jogoAtestar = JogoRandom.gerarJogoAleatorio(15, 25);
		Jogo jogo = new Jogo(2,5,6,7,8,10,11,12,13,15,16,18,19,21,22);
		
		System.out.println("Pares: " + jogoAtestar.getPares());
		System.out.println("SomaTotal: " + jogoAtestar.getSoma());
		
		System.out.println("Pares: " + jogo.getPares());
		System.out.println("SomaTotal: " + jogo.getSoma());
	}
}
