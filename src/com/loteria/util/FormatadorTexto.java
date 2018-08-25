package com.loteria.util;

import java.util.List;
import java.util.Map;

public class FormatadorTexto {

	public String formata(Map<Integer, Integer> map) {
		StringBuilder builder = new StringBuilder();
		map.forEach((key, value) -> {
			if(key > 0 && key < 10) {
				builder.append("[0" + key + "]=" + value + " ");
				if(key == 5) {
					builder.append("\n");
				}
			} else {
				builder.append("[" + key + "]=" + value + " ");
				if(key == 10 || key == 15 || key == 20) {
					builder.append("\n");
				}
			}
		});
		
		return builder.toString();
	}
	
	public String formataMaisSairam(Map<Integer, List<Integer>> map) {
		StringBuilder builder = new StringBuilder();
		for(int chave = 10; chave >= 0; chave--) {
			if(chave < 10) {
				builder.append("[0" + chave + "]= ");
			} else {
				builder.append("[" + chave + "]= ");
			}
			map.get(chave).forEach(numero -> {
				builder.append(numero + " ");
			});
			builder.append("\n");
		}
		return builder.toString();
	}

}
