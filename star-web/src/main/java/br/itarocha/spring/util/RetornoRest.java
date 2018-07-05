package br.itarocha.spring.util;

import java.util.List;

import org.springframework.validation.ObjectError;

public class RetornoRest {

	private String retorno;
	private String mensagem;
	private Object data;
	private Validador validador;

	public RetornoRest() {}
	
	public RetornoRest(String retorno, String mensagem) {
		this.retorno = retorno;
		this.mensagem = mensagem;
	}

	public RetornoRest(String retorno, String mensagem, Object data) {
		this(retorno, mensagem);
		this.setData(data);
	}
	
	public RetornoRest(String retorno, Object data) {
		this.retorno = retorno;
		this.data = data;
	}

	public RetornoRest(String retorno, Validador validador) {
		this.retorno = retorno;
		this.validador = validador;
		if ((validador != null) && (validador.hasErrors())) {
			this.setMensagem("Foram encontrados erros de validação");
		} 
	}
	
	public String getRetorno() {
		return retorno;
	}
	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public Validador getValidador() {
		return this.validador;
	}
	
}
