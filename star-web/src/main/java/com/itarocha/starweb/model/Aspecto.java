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

import org.hibernate.validator.constraints.NotEmpty;

@Entity()
public class Aspecto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message="Planeta de Origem é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoPlaneta planetaOrigem;

	@NotEmpty(message="Planeta de Destino é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoPlaneta planetaDestino;

	@NotEmpty(message="Aspecto é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoAspecto aspecto;
	
	@Lob 
	@Basic(fetch=FetchType.LAZY)
	private String texto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPlaneta getPlanetaOrigem() {
		return planetaOrigem;
	}

	public void setPlanetaOrigem(TipoPlaneta planetaOrigem) {
		this.planetaOrigem = planetaOrigem;
	}

	public TipoPlaneta getPlanetaDestino() {
		return planetaDestino;
	}

	public void setPlanetaDestino(TipoPlaneta planetaDestino) {
		this.planetaDestino = planetaDestino;
	}

	public TipoAspecto getAspecto() {
		return aspecto;
	}

	public void setAspecto(TipoAspecto aspecto) {
		this.aspecto = aspecto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
