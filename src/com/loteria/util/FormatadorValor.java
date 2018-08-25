package com.loteria.util;

public class FormatadorValor {

	public static String formataValorInteiro(long valor) {
		String valorString = String.valueOf(valor);
		StringBuilder builder = new StringBuilder();
		for(int index = valorString.length(); index > 0; index--) {
			if(builder.length() == 2 || builder.length() == 6 || builder.length() == 10) {
				builder.append(valorString.charAt(index - 1) + ".");
			}else {
				builder.append(valorString.charAt(index - 1));
			}
		}
		builder = builder.reverse();
		if(builder.charAt(0) == '.') {
			builder = builder.deleteCharAt(0);
		}
		return builder.toString();
	}
	
	public static void main(String[] args) {
		String string = formataValorInteiro(256987451L);
		System.out.println(string);
	}
}
