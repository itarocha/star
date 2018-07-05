package com.itarocha.starweb.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity()
public class MapaPlanetaAspecto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull(message="Planeta de Origem é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoPlaneta planetaOrigem;

	@NotNull(message="Planeta Destino é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoPlaneta planetaDestino;
	
	@NotNull(message="Tipo de Aspecto é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoAspecto aspecto;

	@NotNull(message="Tipo de Relação é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoRelacao tipoRelacao;
	
	@NotNull(message="Conferido é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoLogico conferido;

	@ManyToOne
	@JoinColumn(name = "id_mestre")
	private MapaPlanetaAspecto aspectoMestre;
	
	@Lob 
	@Basic(fetch=FetchType.LAZY)
	//@NotNull(message="Texto é obrigatório") // não é mais obrigatório
	private String texto;
	
	@Transient
	private String descricaoResumida;
	
	
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

	public TipoRelacao getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(TipoRelacao tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	public MapaPlanetaAspecto getAspectoMestre() {
		return aspectoMestre;
	}

	public void setAspectoMestre(MapaPlanetaAspecto aspectoMestre) {
		this.aspectoMestre = aspectoMestre;
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

	@Transient
	public boolean getFoiConferido() {
		return TipoLogico.S.equals(this.conferido);
	}
	
	public String getDescricaoResumida() {
		return String.format("%s %s %s", this.planetaOrigem.getDescricao(), this.getAspecto().getDescricao(), this.planetaDestino.getDescricao()).toUpperCase();
	}
	
}
