package com.loteria.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ListaJogos {

	private Map<Integer, Jogo> jogos;
	private int quantidadeJogos = 0;
	private Map<Integer, Integer> frequencia; 
	
	public ListaJogos(){
		jogos = new HashMap<>();
		frequencia = new HashMap<>();
		
	}
	
	public ListaJogos(Jogo... jogos){
		for (Jogo jogo : jogos) {
			adicionar(jogo);
		}
	}
	
	public void adicionar(Jogo jogo){
		this.quantidadeJogos += 1;
		this.jogos.put(this.quantidadeJogos, jogo);
	}
	
	public Map<Integer, Jogo> getJogos() {
		return jogos;
	}
	
	public int getQuantidadeJogos() {
		return jogos.size();
	}
	
	public Map<Integer, Integer> frequenciaDosNumeros(){
		for(int indice = 1; indice <= 25; indice++ ){ frequencia.put(indice, 0); }
		for(int i = 1; i < this.jogos.size(); i++){
			Set<Integer> jogo = this.jogos.get(i).getJogo();
			for (Integer numero : jogo) {
				frequencia.put(numero, frequencia.get(numero) + 1);
			}
		}
		return frequencia;
	}
	
	public Integer frequenciaNumeroNaLista(int numero) {
		return frequencia.get(numero);
	}
	
	public void frequenciaDosNumerosPrint(){
		Map<Integer, Integer> map = frequenciaDosNumeros();
		for (int i = 1; i <= map.size(); i++) {
			if(i == 6 || i == 11 || i == 16 || i == 21){ System.out.println(); }
			if(i < 10) { 
				System.out.print("0" + i + " = " + map.get(i) + "  "); 
			}else{
				System.out.print(i + " = " + map.get(i) + "  ");
			}
		}
	}
	
	public String mostraJogos() {
		StringBuilder jogosString = new StringBuilder();
		for(int numeroJogo = 1; numeroJogo <= getQuantidadeJogos(); numeroJogo++ ) {
			jogosString.append(this.jogos.get(numeroJogo).toString() + "\n");
		}
		return jogosString.toString();
	}
	
}
