package com.itarocha.starweb.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itarocha.starweb.model.AspectoInput;
import com.itarocha.starweb.model.MapaPlanetaAspecto;
import com.itarocha.starweb.model.TipoAspecto;
import com.itarocha.starweb.model.TipoPlaneta;
import com.itarocha.starweb.model.TipoRelacao;
import com.itarocha.starweb.repository.MapaPlanetaAspectoRepository;
import com.itarocha.starweb.service.MapaService;

import br.itarocha.spring.util.CampoErro;
import br.itarocha.spring.util.ErrorsAdapter;
import br.itarocha.spring.util.RetornoRest;
import br.itarocha.spring.util.Validador;

@RestController
@RequestMapping("/api/mapa")
public class ApiMapaController {

	@Autowired 
	private MapaService mapaService;
	
	@Autowired
	private MapaPlanetaAspectoRepository repo;

	
	// GET /api/mapa/aspectos/SAT/QD/NET
	@CrossOrigin
	@RequestMapping("/aspectos/{origem}/{aspecto}/{destino}")
	public RetornoRest getAspecto( @PathVariable("origem") String planetaOrigem, @PathVariable("destino") String planetaDestino, @PathVariable("aspecto") String aspecto) {
		
		MapaPlanetaAspecto ma = mapaService.findAspecto(planetaOrigem, planetaDestino, aspecto);
		if (ma != null) {
			return new RetornoRest("SUCESSO",ma);
		} else {
			return new RetornoRest("ERRO","Não encontrado");
		}
	}
	
	@CrossOrigin
	@PostMapping("/aspectos")
	public RetornoRest salvar(@Valid @RequestBody AspectoInput model, BindingResult result)
	{
		
		Validador v = ErrorsAdapter.resolve(result);
		
		TipoAspecto aspecto = null;
		TipoPlaneta planetaOrigem = null; 
		TipoPlaneta planetaDestino = null; 
		MapaPlanetaAspecto mestre = null;
		
		if (model.getPlanetaOrigem() != null) {
			try {
				planetaOrigem = TipoPlaneta.valueOf(model.getPlanetaOrigem() );
			} catch(Exception e) {
				v.adicionaErro(new CampoErro("planetaOrigem","Valor inválido. Use "+Arrays.asList(TipoPlaneta.values()) ));
			}
		}
		
		if (model.getPlanetaDestino() != null) {
			try {
				planetaDestino = TipoPlaneta.valueOf(model.getPlanetaDestino() );
			} catch(Exception e) {
				v.adicionaErro(new CampoErro("planetaDestino","Valor inválido. Use "+Arrays.asList(TipoPlaneta.values()) ));
			}
		}
		
		if (model.getAspecto() != null) {
			try {
				aspecto = TipoAspecto.valueOf(model.getAspecto() );
			} catch(Exception e) {
				v.adicionaErro(new CampoErro("aspecto","Valor inválido. Use "+Arrays.asList(TipoAspecto.values()) ));
			}
		}

		if ((model.getPlanetaOrigem() != null) && (model.getPlanetaDestino() != null) && (model.getPlanetaOrigem().equals(model.getPlanetaDestino())) ){
			v.adicionaErro(new CampoErro("planetaOrigem","Planeta de Origem e Planeta de Destino não podem ser iguais." ));
		}
		
		if (!v.hasErrors()) {
			MapaPlanetaAspecto mpa = mapaService.findAspecto(model.getPlanetaOrigem(), model.getPlanetaDestino(), model.getAspecto());
			if (mpa != null) {
				v.adicionaErro(new CampoErro("planetaOrigem","Aspecto já existe. Id = "+mpa.getId() ));
			}
		}
		
		if (model.getAspectoMestre() != null) {
			String aspectoMestre = model.getAspectoMestre();
			
			String[] am = aspectoMestre.toUpperCase().split("-");
			
			if (am.length != 3) {
				v.adicionaErro(new CampoErro("aspectoMestre","Aspecto mestre inválido. Use o padrão 'OOO-AA-DDD' OOO = Planeta Origem, AA = Aspecto, DDD = Planeta Destino "+ model.getAspectoMestre() ));
			} else {
				mestre = mapaService.findAspecto(am[0], am[2], am[1]);
				if (mestre == null) {
					v.adicionaErro(new CampoErro("aspectoMestre","AspectoMestre não existe." ));
				}
			}
		}

		if ( v.hasErrors()){
			return new RetornoRest("ERRO",v);
		}
		
		MapaPlanetaAspecto toSave = new MapaPlanetaAspecto();
		toSave.setPlanetaOrigem(planetaOrigem);
		toSave.setPlanetaDestino(planetaDestino);
		toSave.setAspecto(aspecto);
		toSave.setTexto(model.getTexto());
		toSave.setTipoRelacao((mestre == null) ? TipoRelacao.MESTRE : TipoRelacao.DETALHE);
		toSave.setAspectoMestre(mestre);
		
		try{
			toSave = repo.save(toSave);
			return new RetornoRest("SUCESSO","Objeto é válido.",toSave);
		} catch (Exception e) {
			return new RetornoRest("ERRO",e.getMessage());
		}
	}
	
	
}
