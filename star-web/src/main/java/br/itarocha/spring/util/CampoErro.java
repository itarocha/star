package br.itarocha.spring.util;

public class CampoErro {
	
	private String campo;
	private String mensagem;
	
	public CampoErro(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagem() {
		return mensagem;
	}

}
