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

import com.itarocha.starweb.model.PlanetaSigno;
import com.itarocha.starweb.model.TipoLogico;
import com.itarocha.starweb.model.TipoPlaneta;
import com.itarocha.starweb.model.TipoSigno;
import com.itarocha.starweb.repository.PlanetaSignoRepository;


@Controller
@RequestMapping("planetasigno")
public class PlanetasSignosController {
	
	private static final String PAGINA = "planetasigno";
	private static final String PAGINA_INDEX = "planetasigno/index";
	private static final String PAGINA_EDIT = "planetasigno/edit";
	private static final String PAGINA_CONSULTAS = "planetasigno/index";
	//private static final String PAGINA_REDIRECT = "redirect:/clientes";
	private static final String PAGINA_REDIRECT = "redirect:/planetasigno";
	private static final String NOME_CLASSE = "PlanetaSigno";
	
	
	@Autowired
	private PlanetaSignoRepository service;
	
	/*

	@Autowired
	private ConsultaService consultasService;
	
	@Autowired
	private Mensagens mensagens;
	*/
	
	@RequestMapping
	public ModelAndView all()
	{
		ModelAndView mv = new ModelAndView(PAGINA_INDEX);
		
		List<PlanetaSigno> lista = service.findAllOrderByPlanetaAndSigno();
		mv.addObject("lista",lista);
		
		return mv;
	}
	
	
	/*
	private boolean userHasAuthority(String authority, Authentication auth)
	{
	    for (GrantedAuthority grantedAuthority :  auth.getAuthorities()) {
	        if (authority.equals(grantedAuthority.getAuthority())) {
	            return true;
	        }
	    }
	    return false;
	}	
	*/
	
	@RequestMapping("/new")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView(PAGINA_EDIT);
		PlanetaSigno model = new PlanetaSigno();
		mv.addObject("model", model);
		return mv;
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable("id") PlanetaSigno model){
		ModelAndView mv = new ModelAndView(PAGINA_EDIT);
		mv.addObject("model", model);
		return mv;
	}
	
	@PostMapping()
	public String salvar(	@Valid @ModelAttribute("model") PlanetaSigno model, 
							BindingResult bindingResult, 
							final RedirectAttributes attributes,
							HttpSession session)
	{
		if (bindingResult.hasErrors()){
			return PAGINA_EDIT;
		}
		
		//model.setNome(model.getNome().toUpperCase());
		//model.setCidade(model.getCidade().toUpperCase());
		
		//constroiMapa(model);
		
		try{
			service.save(model);
			
			//service.gravar(objeto);
			//session.setAttribute("model", model);
			//attributes.addFlashAttribute("model","Itamar");
			//attributes.addFlashAttribute("mensagem", mensagens.getMensagemGravacaoSucesso(NOME_CLASSE));
			return PAGINA_REDIRECT;
		}catch(IllegalArgumentException e){
			return PAGINA_EDIT;
		}
	}
	
	/*
	private Mapa constroiMapa(Cliente model){
		Mapa mapa = MapaBuilder.getInstance().build(model.getNome(), model.getDataNascimento(), model.getHoraNascimento(), model.getCidade(), model.getUf());
		if (mapa != null) {
			String json = new DecoradorMapa(mapa).getJSON();
			System.out.println(json);
			//return json;
		} else {
			System.out.println("MAPA ESTÁ NULO");
		}	
		return mapa;
	}
	*/
	
	/*
	@RequestMapping("/consultas/{id}")
	public ModelAndView consultas(@PathVariable("id") Cliente model){
		ModelAndView mv = new ModelAndView(PAGINA_CONSULTAS);
		List<Consulta> consultas = consultasService.buscarConsultasPorCliente(model);
		
		consultas.sort((c1, c2) -> c1.getDataCalculada().compareTo(c2.getDataCalculada()));
		
		List<ConsultaGrafico> grafico = consultasService.getGrafico(model);

		mv.addObject("graficoData", grafico);
		mv.addObject("model", model);
		mv.addObject("consultas", consultas);
		return mv;
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, final RedirectAttributes attributes){
		service.excluir(id);
		attributes.addFlashAttribute("mensagem", "Cliente excluído");
		return PAGINA_REDIRECT;
	}

	@RequestMapping(value="/consultas/delete/{id}", method=RequestMethod.DELETE)
	public String excluirConsulta(@PathVariable Long id, final RedirectAttributes attributes){
		Consulta c = consultasService.findById(id);
		
		if (c != null) {
			//System.out.println(c.getId() + " - " +c.getDataAgenda());
			consultasService.excluir(id);
			attributes.addFlashAttribute("mensagem", "Consulta excluída");
			String retorno = String.format("redirect:/clientes/consultas/%d", c.getCliente().getId());
			System.out.println(retorno);
			return retorno;
		} else {
			attributes.addFlashAttribute("mensagem", "Consulta não encontrada");
			return "redirect:/clientes";
		}
	}
	
	@ModelAttribute("todosSexo")
	public List<Sexo> todosSexo(){
		return Arrays.asList(Sexo.values());
	}
	*/
	
	@ModelAttribute("listaSignos")
	public List<TipoSigno> listaTipoSigno(){
		return Arrays.asList(TipoSigno.values());
	}
	
	@ModelAttribute("listaPlanetas")
	public List<TipoPlaneta> listaPlaneta(){
		return Arrays.asList(TipoPlaneta.values());
	}
	
	@ModelAttribute("listaLogico")
	public List<TipoLogico> listaLogico(){
		return Arrays.asList(TipoLogico.values());
	}
	
}


//<td th:text="${#dates.format(item.dataVisita, 'dd/MM/yyyy')}"></td>



