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

import org.hibernate.validator.constraints.NotEmpty;

@Entity()
public class PlanetaSigno {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	//@NotEmpty(message="Planeta é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoPlaneta planeta;

	//@NotEmpty(message="Signo é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoSigno signo;
	
	@Lob 
	@Basic(fetch=FetchType.LAZY)
	private String texto;

	@NotNull(message="Conferido é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoLogico conferido;
	
	@Transient
	private boolean foiCoferido;
	
	
	public Long getId() {
		return id;
	} 

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPlaneta getPlaneta() {
		return planeta;
	}

	public void setPlaneta(TipoPlaneta planeta) {
		this.planeta = planeta;
	}

	public TipoSigno getSigno() {
		return signo;
	}

	public void setSigno(TipoSigno signo) {
		this.signo = signo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public TipoLogico getConferido() {
		return conferido;
	}

	public void setConferido(TipoLogico conferido) {
		this.conferido = conferido;
	}

	public boolean getFoiConferido() {
		return this.conferido.equals(TipoLogico.S);
	}
	
}
