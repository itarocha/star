package br.itarocha.star;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import br.itarocha.star.model.Cidade;
import br.itarocha.star.util.Funcoes;

public class MapeadorCidades {

	
	private static MapeadorCidades instance = null;
	private static final Map<String, Cidade> mapCidades = new HashMap<String, Cidade>();
	private static final Map<String,Integer> mapFuso;
	static
	{
		mapFuso = new HashMap<String,Integer>();
		mapFuso.put("AC",-5);
		mapFuso.put("AL",-3);
		mapFuso.put("AM",-4);
		mapFuso.put("AP",-3);
		mapFuso.put("BA",-3);
		mapFuso.put("CE",-3);
		mapFuso.put("DF",-3);
		mapFuso.put("ES",-3);
		mapFuso.put("GO",-3);
		mapFuso.put("MA",-3);
		mapFuso.put("MG",-3);
		mapFuso.put("MS",-4);
		mapFuso.put("MT",-4);
		mapFuso.put("PA",-3);
		mapFuso.put("PB",-3);
		mapFuso.put("PE",-3);
		mapFuso.put("PI",-3);
		mapFuso.put("PR",-3);
		mapFuso.put("RJ",-3);
		mapFuso.put("RN",-3);
		mapFuso.put("RO",-4);
		mapFuso.put("RR",-4);
		mapFuso.put("RS",-3);
		mapFuso.put("SC",-3);
		mapFuso.put("SE",-3);
		mapFuso.put("SP",-3);
		mapFuso.put("TO",-3);
	}
	
	public static MapeadorCidades getInstance() {
		if (instance == null) 
			instance = new MapeadorCidades();
		return instance;
	}
	

	private MapeadorCidades() {
		this.carregarArquivoCidades();
	}

	public Cidade getCidade(String cidade, String uf) {
		String key = buildKey(cidade, uf);
		Cidade c = mapCidades.get(key);
		return c;
	}
	
	public Map<String, Cidade> getCidades(){
		return this.mapCidades;
	}

	private String buildKey(String cidade, String uf) {
		String s = Funcoes.removerAcentos(cidade).toUpperCase();
		s = s.replaceAll(" ","");
		return String.format("%s.%s", uf, s);
	}
	
	private void carregarArquivoCidades() {
		URL arquivoCSV = MapeadorCidades.class.getClassLoader().getResource("ephe/cidades_brasil.csv");
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ",";
		try {
			br = new BufferedReader(new InputStreamReader(arquivoCSV.openStream()));
			//br = new BufferedReader(new FileReader(arquivoCSV.toString()));
			int i = -1;
			while (((linha = br.readLine()) != null)) {
				i++;
				if (i > 0) {
					/*
					 * Ler o arquivo //ephe//cidades_brasil.csv
					 * "codigo","lat","e/w","lon","n/s","nome sem acento"
					 * ,"uf","nome original","altitude","area"
					 * 1,"-16.45.26","W","-49.26.15","S","abadia de goias"
					 * ,"GO","Abadia de Goi�s",898.000,136.900
					 * 2,"-18.29.08","W","-47.24.11","S","abadia dos dourados"
					 * ,"MG","Abadia dos Dourados",742.000,897.400
					 * 3,"-16.12.15","W","-48.42.25","S","abadiania","GO",
					 * "Abadi�nia",1052.000,1047.700
					 */
					String[] cidade = linha.split(csvDivisor);

					Cidade c = new Cidade();
					c.setCodigo(Integer.parseInt(cidade[0]));
					c.setNomeSemAcento(cidade[5].replaceAll("\"", ""));
					c.setUF(cidade[6].replaceAll("\"", ""));
					c.setNomeOriginal(cidade[7].replaceAll("\"", ""));
					c.setLatitude(cidade[1].replaceAll("\"", ""));
					c.setLongitude(cidade[3].replaceAll("\"", ""));
					
					c.setFuso(mapFuso.get(c.getUF()));

					String key = buildKey(c.getNomeSemAcento(), c.getUF());
					this.mapCidades.put(key,c);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

/*

	AC,-5
	AL,-3
	AM,-4
	AP,-3
	BA,-3
	CE,-3
	DF,-3
	ES,-3
	GO,-3
	MA,-3
	MG,-3
	MS,-4
	MT,-4
	PA,-3
	PB,-3
	PE,-3
	PI,-3
	PR,-3
	RJ,-3
	RN,-3
	RO,-4
	RR,-4
	RS,-3
	SC,-3
	SE,-3
	SP,-3
	TO,-3


*/