package com.itarocha.starweb.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity()
public class SignoSolar {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoSigno signo;
	
	@NotEmpty(message="Descrição é obrigatório")
	@Size(min = 2, max = 64, message="Descrição deve ter entre 2 a 64 caracteres")
	private String descricao;
	
	@Lob 
	@Basic(fetch=FetchType.LAZY)
	private String texto;

	@NotNull(message="Conferido é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoLogico conferido;

	public Long getId() {
		return id;
	} 
	
	public void setId(Long id) {
		this.id = id;
	}

	public TipoSigno getSigno() {
		return signo;
	}

	public void setSigno(TipoSigno signo) {
		this.signo = signo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Transient
	public boolean getFoiConferido() {
		return TipoLogico.S.equals(this.conferido);
	}

	public TipoLogico getConferido() {
		return conferido;
	}

	public void setConferido(TipoLogico conferido) {
		this.conferido = conferido;
	}
	
}
