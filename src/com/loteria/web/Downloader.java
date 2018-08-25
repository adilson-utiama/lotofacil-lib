package com.loteria.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class Downloader {
	
	private final static String URL_ARQUIVO = "http://www1.caixa.gov.br/loterias/_arquivos/loterias/D_lotfac.zip";

	public void baixar(String urlArquivo) {
		
		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
	
		URL url;
		try {
			url = new URL(urlArquivo);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			String message = connection.getResponseMessage();
			String contentType = connection.getContentType();
			System.out.println(message + " : " + contentType);
			
			File baixado = new File("baixado.zip");
			
			InputStream inputStream = connection.getInputStream();
			FileOutputStream outputStream = new FileOutputStream(baixado);
			
			int bytes = 0;

	        while ((bytes = inputStream.read()) != -1) {
	        	outputStream.write(bytes);
	        }

	        inputStream.close();
	        outputStream.close();
	        connection.disconnect();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Arquivo baixado com sucesso!");
	}
	
	public void baixarComCommonIO(String urlArquivo) {
		
		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
		
		try {
			URL url = new URL(urlArquivo);
			FileUtils.copyURLToFile(url, new File("baixado-commons-io.zip"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Arquivo baixado com sucesso!");
	}
	
	public static void main(String[] args) {
		
		new Downloader().baixarComCommonIO(URL_ARQUIVO);
	}
}
