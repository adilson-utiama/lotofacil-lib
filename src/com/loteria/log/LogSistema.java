package com.loteria.log;

public class LogSistema {

	public static void log(Object... objetos) {
		for (Object object : objetos) {
			System.out.println("##LOG:: " + object.getClass().getSimpleName() + " : " + object.toString());
		}
	}
}
