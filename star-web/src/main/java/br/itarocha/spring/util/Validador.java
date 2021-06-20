package br.itarocha.spring.util;

import java.util.ArrayList;
import java.util.List;

public class Validador {

	private List<CampoErro> erros;
	
	public Validador() {
		erros = new ArrayList<CampoErro>();
	}
	
	public boolean hasErrors() {
		return  erros.size() > 0; 
	}
	
	public void adicionaErro(CampoErro erro) {
		erros.add(erro);
	}
	
	public void limpar() {
		erros.clear();
	}
	
	public List<CampoErro> getErros(){
		return this.erros;
	}
}
