package com.itarocha.starweb.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itarocha.starweb.model.SignoSolar;
import com.itarocha.starweb.model.TipoLogico;
import com.itarocha.starweb.model.TipoSigno;
import com.itarocha.starweb.repository.SignoSolarRepository;

@Controller
@RequestMapping("solares")
public class SignosSolaresController {
	
	private static final String PAGINA = "solares";
	private static final String PAGINA_INDEX = "solares/index";
	private static final String PAGINA_EDIT = "solares/edit";
	private static final String PAGINA_CONSULTAS = "solares/index";
	private static final String PAGINA_REDIRECT = "redirect:/solares";
	private static final String NOME_CLASSE = "SignosSolares";
	
	
	@Autowired
	private SignoSolarRepository service;
	
	@RequestMapping
	public ModelAndView all()
	{
		ModelAndView mv = new ModelAndView(PAGINA_INDEX);
		
		List<SignoSolar> lista = service.findAll();
		mv.addObject("lista",lista);
		
		return mv;
	}
	
	@RequestMapping("/new")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView(PAGINA_EDIT);
		SignoSolar model = new SignoSolar();
		mv.addObject("model", model);
		return mv;
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable("id") SignoSolar model){
		ModelAndView mv = new ModelAndView(PAGINA_EDIT);
		mv.addObject("model", model);
		return mv;
	}
	
	@PostMapping()
	public String salvar(	@Valid @ModelAttribute("model") SignoSolar model, 
							BindingResult bindingResult, 
							final RedirectAttributes attributes,
							HttpSession session)
	{
		if (bindingResult.hasErrors()){
			return PAGINA_EDIT;
		}
		
		try{
			service.save(model);
			return PAGINA_REDIRECT;
		}catch(IllegalArgumentException e){
			return PAGINA_EDIT;
		}
	}
	
	@ModelAttribute("listaSignos")
	public List<TipoSigno> listaTipoSigno(){
		return Arrays.asList(TipoSigno.values());
	}
	
	@ModelAttribute("listaLogico")
	public List<TipoLogico> listaLogico(){
		return Arrays.asList(TipoLogico.values());
	}
	
}


//<td th:text="${#dates.format(item.dataVisita, 'dd/MM/yyyy')}"></td>



