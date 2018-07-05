package br.itarocha.spring.util;

import java.util.ArrayList;
import java.util.List;

public class Pesquisa {

	private List<LinhaPesquisa> filtro = new ArrayList<LinhaPesquisa>();
	
	public Pesquisa() {
		
	}

	public List<LinhaPesquisa> getFiltro() {
		return filtro;
	}

	public void setFiltro(List<LinhaPesquisa> filtro) {
		this.filtro = filtro;
	}
	
}
