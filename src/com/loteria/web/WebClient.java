package com.loteria.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WebClient {
	
	private static final String RESULTADO_ORDENADO = "resultadoOrdenado";
	private static final String NUMERO_CONCURSO = "nu_concurso";
	//private String webService = "http://wsloterias.azurewebsites.net/api/sorteio/getresultado/";
	private static final String URL_CAIXA = "http://loterias.caixa.gov.br/wps/portal/!ut/p/a1/04_Sj9CPykssy0xPLMnM"
			+ "z0vMAfGjzOLNDH0MPAzcDbz8vTxNDRy9_Y2NQ13CDA0sTIEKIoEKnN0dPUzMfQwMDEwsjAw8XZw8XMwt"
			+ "fQ0MPM2I02-AAzgaENIfrh-FqsQ9wBmoxN_FydLAGAgNTKEK8DkRrACPGwpyQyMMMj0VAcySpRM!/dl5/"
			+ "d5/L2dBISEvZ0FBIS9nQSEh/pw/Z7_61L0H0G0J0VSC0AC4GLFAD2003/res/id=buscaResultado/"
			+ "c=cacheLevelPage/=/?timestampAjax=1521758990260&concurso=";
	
	public List<Integer> getResultado(int concurso){
		try {
			return buscarSorteio(concurso);
			
		} catch (IOException | ParseException e) {
			throw new RuntimeException("Erro ao tentar acessar os resultados" + e);
		}
	}
	
	public String getResultadoEmString(int concurso){
		try {
			StringBuilder numeros = new StringBuilder();
			List<Integer> sorteio = buscarSorteio(concurso);
			int contador = 1;
			for (Integer integer : sorteio) {
				if(contador != 15){
					if(integer > 9){
						numeros.append(String.valueOf(integer) + " ");
					}else{
						numeros.append("0" + String.valueOf(integer) + " ");
					}
					
				}else{
					numeros.append(String.valueOf(integer));
				}
				contador++;
			}
			return numeros.toString();
			
		} catch (IOException | ParseException e) {
			throw new RuntimeException("Erro ao tentar acessar os resultados" + e);
		}
	}


	
	public Integer getUltimoConcurso() {
		String valor = getJsonItemValue("", NUMERO_CONCURSO);
		Integer concurso = 0;
		try{
			concurso = Integer.parseInt(valor);
		} catch (NumberFormatException e){
			throw new RuntimeException(e);
		}	
		return concurso;
	}

	private List<Integer> buscarSorteio(int concurso)
			throws MalformedURLException, IOException, ParseException {
		String concursoString = String.valueOf(concurso);
		String resultadoRetornado = getJsonItemValue(concursoString, RESULTADO_ORDENADO);
		return retornaListaNumeros(resultadoRetornado);
	}
	
	private String getJsonItemValue(String concurso, String key){
		HttpURLConnection conexao = configuraConexao(URL_CAIXA, concurso);
		String retornaRespostaDaRequisicao = retornaRespostaDaRequisicao(conexao);
		return valorDeJSONPropertie(retornaRespostaDaRequisicao, key);
	}
	
	private HttpURLConnection configuraConexao(String urlConexao, String concurso) {
		URL url;
		try {
			url = new URL(urlConexao + concurso);
			CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
			return (HttpURLConnection) url.openConnection();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private List<Integer> retornaListaNumeros(String resultadoRetornado) {
		String[] arrayNumerosSorteados = resultadoRetornado.split("-");
		List<Integer> resultadoRetorno = new ArrayList<>();
		for (String num : arrayNumerosSorteados) {
			resultadoRetorno.add(Integer.parseInt(num));
		}
		return resultadoRetorno;
	}
	
	private String valorDeJSONPropertie(String json, String chave) {
		JSONParser parser = new JSONParser();
		try{
			JSONObject jsonObject = (JSONObject) parser.parse(json);
			return (String) jsonObject.get(chave);
		} catch(ParseException err){
			throw new RuntimeException(err.getMessage());
		}
	}

	private String retornaRespostaDaRequisicao(HttpURLConnection conexao){
		InputStream inputStream;
		Scanner leitor = null;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			inputStream = conexao.getInputStream();
			leitor = new Scanner(inputStream);
			while(leitor.hasNext()){
				stringBuilder.append(leitor.nextLine());
			}
			leitor.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			conexao.disconnect();
		}
		return stringBuilder.toString();
	}
	
	public static void main(String[] args) {
		WebClient client = new WebClient();
		Integer ultimoConcurso = client.getUltimoConcurso();
		System.out.println("Ultimo Concurso: " + ultimoConcurso);
				
		List<Integer> i = client.getResultado(1660);
		i.forEach(numero -> System.out.print(numero + " "));
		
		System.out.println();
		
		String resultadoEmString = client.getResultadoEmString(client.getUltimoConcurso());
		System.out.println(resultadoEmString);

	}
	
}
