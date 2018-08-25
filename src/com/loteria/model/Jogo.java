package com.loteria.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Jogo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Set<Integer> jogo = new HashSet<>();
	private int limite;
	
	public Jogo(int limite){
		this.limite = limite;
	}
	
	public Jogo(int... numeros){
		for (int i = 0; i < numeros.length; i++) {
			this.jogo.add(numeros[i]);
		}
	}
	
	public Jogo(Collection<Integer> jogo){
		this.jogo.addAll(jogo);
	}
	
	public Set<Integer> getJogo() {
		return new HashSet<>(Collections.unmodifiableCollection(jogo));
	}
	
	public List<Integer> getJogoLista(){
		return new ArrayList<>(Collections.unmodifiableCollection(jogo));
	}
	
	public void addNumero(Integer numero){
		if(this.jogo.size() < limite){
			this.jogo.add(numero);
		}else{
			throw new RuntimeException("Quantidade de numeros excedidos.");
		}
	}
	
	public int getSoma(){
		int soma = 0;
		for (Integer n : jogo) {
			soma += n;
		}
		return soma;
	}
	
	public int getPares(){
		int pares = 0;
		for (Integer n : jogo) {
			if(n % 2 == 0) pares += 1;
		}
		return pares;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		this.jogo.forEach(numero -> { 
			if(numero < 10){
				builder.append("0" + numero + " ");
			} else {
				builder.append(String.valueOf(numero) + " ");
			}
		});
		return  builder.toString();
	}
	
	public String saidaFormatada() {
		return toString();
	}

	public void addNumeros(int ... numeros) {
		for (int i : numeros) {
			jogo.add(i);
		}
		
	}
}
