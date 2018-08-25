package com.loteria.main;

import com.loteria.service.ConcursosService;

public class TesteAtualizaBanco {

	public static void main(String[] args) {
		
		ConcursosService service = new ConcursosService();
		int ultimoResultado = service.ultimoResultado();
		System.out.println(ultimoResultado);
		service.atualizaResultados();
	}
}
