package com.loteria.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import com.loteria.geradores.JogoRandom;
import com.loteria.model.Jogo;

public class LoteriaOutput {
	
	private static final String CAMINHO_ARQUIVO = "C://___SAIDAS/arquivos_gerados/";
	private static final String EXTENSAO = ".txt";

	
	public void escreveEmArquivo(String nomeArquivo, Jogo jogo) throws IOException {
		
		LocalDateTime data = LocalDateTime.now();
		int dia = data.getDayOfMonth();
		int mes = data.getMonthValue();
		int ano = data.getYear();
		String dataFormatada = dia + "-" + mes + "-" + ano;
		
		nomeArquivo = dataFormatada + "_" + nomeArquivo;
		
		File caminho = new File(CAMINHO_ARQUIVO);
		File arquivo = new File(CAMINHO_ARQUIVO + nomeArquivo + EXTENSAO);
		if(caminho.exists()) {
			FileWriter writer = new FileWriter(arquivo, true);
			writer.write(jogo.saidaFormatada());
			writer.write(System.lineSeparator());
			writer.close();
			System.out.println("Numeros do Jogo adicionado com sucesso.");
		} else {
			boolean criou = caminho.mkdirs();
			if(criou) System.out.println("Diretorio criado com sucesso!");
			LocalDateTime now = LocalDateTime.now();
			
			PrintWriter saida = new PrintWriter(arquivo);
			saida.println("Data de Criação: " + now);
			saida.println(jogo.saidaFormatada());
			saida.close();

		}
		
	}
	
	public static void main(String[] args) {
		
		for(int i = 0; i < 10; i++) {
			Jogo jogo = JogoRandom.gerarJogoAleatorio(15, 25);
			try {
				new LoteriaOutput().escreveEmArquivo("teste", jogo);
			} catch (IOException e) {
				System.out.println("Erro ao escrever arquivo");
				e.printStackTrace();
			}
		}
	}
}
