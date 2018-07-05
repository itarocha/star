package br.itarocha.star.model;

public class OldPlaneta {
	private int id;
	private String sigla;
	private String nome;
	private String letra;

	public OldPlaneta(int id, String sigla, String nome, String letra){
		this.id = id;
		this.sigla = sigla;
		this.nome = nome;
		this.letra = letra;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}
}
