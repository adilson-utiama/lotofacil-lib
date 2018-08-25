package com.loteria.testes;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.loteria.filtros.FiltroSequenciaDosNumeros;
import com.loteria.web.WebClient;

public class TesteSequenciaDosNumeros {

	public static void main(String[] args) {
		WebClient webClient = new WebClient();
        List<Integer> concurso1660 = webClient.getResultado(1672);

        FiltroSequenciaDosNumeros jogo = new FiltroSequenciaDosNumeros(concurso1660);

        System.out.println(jogo);
        System.out.println(jogo.getPares());
        System.out.println(jogo.getImpares());
        System.out.println(jogo.getTotalPares());
        Map<Integer, List<Set<Integer>>> sequencias = jogo.getSequencias();
        System.out.println(sequencias);

	}

}
