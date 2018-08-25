package com.loteria.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.loteria.model.Jogo;
import com.loteria.util.Utilitarios;
import com.loteria.web.WebClient;

public class ConcursosRepository {

	private Database db = new Database();
	
	private final String file = "db/banco.ser";
	private ObjectOutputStream saida = null;
	private ObjectInputStream entrada = null;
	
	private FileOutputStream outputStream = null;
	private FileInputStream inputStream = null;
	
	public ConcursosRepository(){
		createDBFile();
		readDB();
		
	}
	
	public static ConcursosRepository getDatabase(){
		return new ConcursosRepository();
	}
	
	private void saveDB(){
		try {
			outputStream = new FileOutputStream(file);
			saida = new ObjectOutputStream(outputStream);
			saida.writeObject(this.db);
			System.out.print("## Salvando dados no database... ");
			saida.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Arquivo n�o encontrado!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("OK");
	}
	
	private void readDB(){
		try {
			inputStream = new FileInputStream(file);
			entrada = new ObjectInputStream(inputStream);
			this.db = (Database) entrada.readObject();
			System.out.print("## Lendo dados do database.... ");
			entrada.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("OK");
	}
	
	private void createDBFile(){
		Path path = Paths.get(file);
		try {
			if(!Files.exists(path)){
				Files.createFile(path);
				saveDB();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void importarDadosParaObjeto() {
		if(db.concursosCadastrados() == 0){
			try {
				Scanner entrada = new Scanner(new FileInputStream("csv/loteria.csv"));
				String linha = null;
				while (entrada.hasNextLine()) {
					linha = entrada.nextLine();
					if (!linha.matches(",,,,,,,,,,,,,,")) {
						String sortNumeros = Utilitarios.sortNumeros(linha);
						String[] strings = sortNumeros.split(" ");
						
						Jogo jogo = new Jogo(15);
						for (String n : strings) {
							n.trim();
							jogo.addNumero(Integer.parseInt(n));
						}
						db.addJogo(jogo);
					}
				}
				entrada.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			saveDB();
		} else {
			System.out.println("jogos ja adicionados.");
		}
		
	}

	public void atualizar() {
		WebClient client = new WebClient();
		int ultimoConcurso = client.getUltimoConcurso();
		
		System.out.println("[info] Concursos cadatrados atualmente : " + db.getDatabase().size());
		System.out.println("[info] Ultimo concurso: " + ultimoConcurso);
				
		int con = db.getDatabase().size() + 1;
		for(int i = con; i <= ultimoConcurso; i++){
			List<Integer> resultado = client.getResultado(i);
			Jogo jogo = new Jogo(15);
			for (Integer numero : resultado) {
				
				jogo.addNumero(numero);
				
				incluirConcurso(i, jogo);
			}
			System.out.println("Concurso " + i + " incluido com sucesso!");
		}
		saveDB();
		
	}

	private void incluirConcurso(int concurso, Jogo jogo) {
		Map<Integer, Jogo> database = db.getDatabase();
		database.put(concurso, jogo);
		
	}

	public int getConcursosCadastrados() {
		return db.concursosCadastrados();
	}
	
	public Map<Integer, Jogo> getJogos(){
		return Collections.unmodifiableMap(db.getDatabase());
	}

	public void adicionaJogo(Jogo jogo) {
		db.addJogo(jogo);
	}

	public Jogo getJogo(int concurso) {
		return db.get(concurso);
	}

}
