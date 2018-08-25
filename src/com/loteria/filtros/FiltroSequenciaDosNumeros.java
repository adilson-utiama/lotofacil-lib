package com.loteria.filtros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.loteria.model.Jogo;

public class FiltroSequenciaDosNumeros {

    private Set<Integer> jogo = new HashSet<>();

    public FiltroSequenciaDosNumeros() {}

    public FiltroSequenciaDosNumeros(List<Integer> concurso) {
        if (!concurso.isEmpty() || (concurso.size() != 15)) {
            jogo.addAll(concurso);
        }
    }

    public FiltroSequenciaDosNumeros(int... numeros) {
        for(int numero : numeros) this.jogo.add(numero);
    }

    public FiltroSequenciaDosNumeros(Jogo jogo) {
		this.jogo = jogo.getJogo();
	}

	public Integer getTotalPares(){
        Integer pares = 0;
        for(Integer numero : jogo){
            if(isPar(numero)){
                pares++;
            }
        }
        return pares;
    }

    public void adicionaNumero(Integer numero){
        if(this.jogo.size() < 15){
            this.jogo.add(numero);
        }
    }

    public List<Integer> getPares(){
        List<Integer> pares = new ArrayList<>();
        for(Integer numero : jogo){
            if(isPar(numero)){
                pares.add(numero);
            }
        }
        return pares;
    }

    public List<Integer> getImpares(){
        List<Integer> impares = new ArrayList<>();
        for(Integer numero : jogo){
            if(!isPar(numero)) impares.add(numero);
        }
        return impares;
    }

    public Map<Integer, List<Set<Integer>>> getSequencias() {
        Map<Integer, List<Set<Integer>>> sequencias = new HashMap<>();
        List<Set<Integer>> listaDeNumerosSequenciais = new ArrayList<>();
        Set<Integer> numerosEmSequencia = new HashSet<>();

        Integer[] arrayNumerosDoJogo = converteJogoParaArray();
        int contador = 1;

        for(int index = 0; index < this.jogo.size(); index++)
            if (proximoNumeroEhSequencia(arrayNumerosDoJogo, index)) {
                numerosEmSequencia.add(arrayNumerosDoJogo[index]);
                numerosEmSequencia.add(arrayNumerosDoJogo[index + 1]);
                contador = numerosEmSequencia.size();
            } else if (existeChaveNaListaSequencia(sequencias, numerosEmSequencia)) {
                sequencias.get(numerosEmSequencia.size()).add(numerosEmSequencia);
                numerosEmSequencia = new HashSet<>();
                contador = 1;
            } else {
                if (!numerosEmSequencia.isEmpty()) {
                    listaDeNumerosSequenciais.add(numerosEmSequencia);
                }
                numerosEmSequencia = new HashSet<>();
                if (!listaDeNumerosSequenciais.isEmpty()) {
                    sequencias.put(contador, listaDeNumerosSequenciais);
                }
                contador = 1;
                listaDeNumerosSequenciais = new ArrayList<>();
            }
        return sequencias;
    }

    private boolean isPar(Integer numero) {
        return numero % 2 == 0;
    }

    private boolean existeChaveNaListaSequencia(Map<Integer, List<Set<Integer>>> sequencias, Set<Integer> numeros) {
        return sequencias.containsKey(numeros.size());
    }

    private boolean proximoNumeroEhSequencia(Integer[] arrayNumeros, int index) {
        return arrayNumeros[index] == arrayNumeros[index + 1] - 1;
    }

    private Integer[] converteJogoParaArray() {
        Integer[] arrayNumeros = new Integer[this.jogo.size() + 1];
        int a = 0;
        for(Integer numero : this.jogo){
            arrayNumeros[a] = numero;
            a++;
        }
        arrayNumeros[a] = 1;
        return arrayNumeros;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        jogo.forEach(numero -> builder.append(numero).append(" "));
        return builder.toString();
    }
}