package com.itarocha.starweb.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itarocha.starweb.model.MapaPlanetaAspecto;
import com.itarocha.starweb.model.TipoAspecto;
import com.itarocha.starweb.model.TipoLogico;
import com.itarocha.starweb.model.TipoPlaneta;
import com.itarocha.starweb.model.TipoRelacao;
import com.itarocha.starweb.repository.MapaPlanetaAspectoRepository;


@Controller
@RequestMapping("aspectos")
public class AspectosController {
	
	private static final String PAGINA_INDEX = "aspectos/index";
	private static final String PAGINA_EDIT = "aspectos/edit";
	private static final String PAGINA_REDIRECT = "redirect:/aspectos";
	
	@Autowired
	private MapaPlanetaAspectoRepository service;
	
	@RequestMapping
	public ModelAndView all()
	{
		ModelAndView mv = new ModelAndView(PAGINA_INDEX);
		
		List<MapaPlanetaAspecto> lista = service.findAll();
		mv.addObject("lista",lista);
		
		return mv;
	}
	
	@RequestMapping("/new")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView(PAGINA_EDIT);
		MapaPlanetaAspecto model = new MapaPlanetaAspecto();
		mv.addObject("model", model);
		return mv;
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable("id") MapaPlanetaAspecto model){
		ModelAndView mv = new ModelAndView(PAGINA_EDIT);
		mv.addObject("model", model);
		return mv;
	}
	
	@PostMapping()
	public String salvar(	@Valid @ModelAttribute("model") MapaPlanetaAspecto model, 
							BindingResult bindingResult, 
							final RedirectAttributes attributes,
							HttpSession session)
	{
		
		Long idAspectoMestre = (model.getAspectoMestre() != null) ? model.getAspectoMestre().getId() : 0;
		
		if (TipoRelacao.DETALHE.equals(model.getTipoRelacao()) && (idAspectoMestre.equals(model.getId()))) {
			ObjectError error = new ObjectError("aspectoMestre","Você não pode fazer auto referência");
			bindingResult.addError(error);		
		}

		if (bindingResult.hasErrors()){
			return PAGINA_EDIT;
		}

		if (TipoRelacao.MESTRE.equals(model.getTipoRelacao())) {
			model.setAspectoMestre(null);
		}
		
		if (TipoRelacao.DETALHE.equals(model.getTipoRelacao())) {
			model.setTexto(null);
		}
		
		try{
			service.save(model);
			return PAGINA_REDIRECT;
		}catch(IllegalArgumentException e){
			return PAGINA_EDIT;
		}
	}
	
	@ModelAttribute("listaAspectos")
	public List<TipoAspecto> listaTipoAspecto(){
		return Arrays.asList(TipoAspecto.values());
	}
	
	@ModelAttribute("listaPlanetas")
	public List<TipoPlaneta> listaPlaneta(){
		return Arrays.asList(TipoPlaneta.values());
	}
	
	@ModelAttribute("listaRelacoes")
	public List<TipoRelacao> listaRelacoes(){
		return Arrays.asList(TipoRelacao.values());
	}
	
	@ModelAttribute("listaLogico")
	public List<TipoLogico> listaLogico(){
		return Arrays.asList(TipoLogico.values());
	}
	
	@ModelAttribute("listaMaster")
	public List<MapaPlanetaAspecto> listaMaster(){
		return service.findAllMaster();
	}
}