package com.itarocha.starweb.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itarocha.starweb.model.Cliente;
import com.itarocha.starweb.model.Interpretacao;
import com.itarocha.starweb.model.TipoLogico;
import com.itarocha.starweb.model.UnidadeFederacao;
import com.itarocha.starweb.service.GeradorPdfService;
import com.itarocha.starweb.service.MapaService;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import br.itarocha.star.ChartPainter;
import br.itarocha.star.DecoradorMapa;
import br.itarocha.star.Mapa;
import br.itarocha.star.MapaBuilder;
import de.thmac.swisseph.SweConst;
import de.thmac.swisseph.SwissEph;

@Controller
@RequestMapping("mapa")
public class MapaController {
	
	private static final String PAGINA = "clientes";
	private static final String PAGINA_INDEX = "mapa/index";
	private static final String PAGINA_EDIT = "mapa/edit";
	private static final String PAGINA_CONSULTAS = "consultas/index";
	//private static final String PAGINA_REDIRECT = "redirect:/clientes";
	private static final String PAGINA_REDIRECT = "redirect:/mapa";
	private static final String NOME_CLASSE = "Cliente";
	private static final String RESOURCE_PATH = ".";
	
	
    public static final String DEST = "results/chapter01/text_paragraph_cardo.pdf";
    
    public static final String FONT_ASTRO = "src/main/resources/fonts/AstroDotBasic.ttf";

    
    public static final String FONT_REGULAR = "src/main/resources/fonts/Cardo-Regular.ttf";
    public static final String FONT_BOLD = "src/main/resources/fonts/Cardo-Bold.ttf";
    public static final String FONT_ITALIC = "src/main/resources/fonts/Cardo-Italic.ttf";
    
    @Autowired
    private MapaService servico;
    
    
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handle(RuntimeException ex){
        ModelAndView model = new ModelAndView("error");
        model.addObject("exception", ex);
        return model;
    }
    
	@RequestMapping
	public ModelAndView pesquisar()
	{
		ModelAndView mv = new ModelAndView(PAGINA_INDEX);
		//mv.addObject("lista",page);
		return mv;
	}
	
	@RequestMapping("/new")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView(PAGINA_EDIT);
		Cliente v = new Cliente();
		mv.addObject("model", v);
		return mv;
	}
	
	//https://memorynotfound.com/spring-mvc-download-file-examples/
	//http://www.baeldung.com/java-pdf-creation
	//http://www.vogella.com/tutorials/JavaPDF/article.html
	//https://developers.itextpdf.com/examples/itext-action-second-edition/chapter-11
	
	/*
	@RequestMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable("id") Cliente model){
		ModelAndView mv = new ModelAndView(PAGINA_EDIT);
		mv.addObject("model", model);
		return mv;
	}
	*/
	
	@PostMapping()
	public String salvar(	@Valid @ModelAttribute("model") Cliente model, 
							BindingResult bindingResult, 
							final RedirectAttributes attributes,
							HttpServletRequest request,
							HttpSession session)
	{
		if (bindingResult.hasErrors()){
			return PAGINA_EDIT;
		}
		
		ServletContext context = request.getSession().getServletContext();
        String appPath = context.getRealPath("");
        appPath = new ClassPathResource("images").getPath();
        URL sqlScriptUrl = MapaController.class.getClassLoader().getResource("static/images/logo-petra.png");
        appPath = MapaController.class.getClassLoader().getResource("static/images").getPath();
        		
        
        
		model.setNome(model.getNome().toUpperCase());
		model.setCidade(model.getCidade().toUpperCase());
		
		List<Interpretacao> lista = constroiMapa(model, appPath);

		try{
			session.setAttribute("path", appPath);
			session.setAttribute("model", model);
			session.setAttribute("lista", lista);

			return PAGINA_REDIRECT;
		}catch(IllegalArgumentException e){
			return PAGINA_EDIT;
		}
	}
	
	private List<Interpretacao> constroiMapa(Cliente model, String path) {
		List<Interpretacao> retorno =  new ArrayList<Interpretacao>();
		MapaBuilder builder = null;
		Calendar calendar = Calendar.getInstance();
		
		try {
			builder = MapaBuilder.getInstance(path);
		
		calendar = Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		SimpleDateFormat sdfd = new SimpleDateFormat("dd/MM/yyyy");
		
		String data = sdfd.format(model.getDataNascimento());
		
		calendar.setTime(sdf.parse(data + " " + model.getHoraNascimento()));
		
		} catch (Exception e) {
			return retorno;
		}
		
		Mapa mapa = builder.build(model.getNome(), calendar, model.getCidade(), model.getUf());
		if (mapa != null) {
			ChartPainter cp = new ChartPainter(mapa,path);
			String json = new DecoradorMapa(mapa).getJSON();
			System.out.println(json);
			
			try {
				GeradorPdfService g = new GeradorPdfService();
				retorno =  g.createArquivo(TipoLogico.S.equals(model.getTudo()), servico, mapa, json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			//System.out.println("MAPA ESTÁ NULO");
		}	
		return retorno;
	}
	
	 
	public void buildFile() {
        BufferedWriter writer = null;
        try {
            //create a temporary file
            String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            File logFile = new File(timeLog);

            // This will output the full path where the file will be written to...
            System.out.println(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write("Hello world!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
    }	
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
	
	@ModelAttribute("todosUF")
	public List<UnidadeFederacao> todosUF(){
		return Arrays.asList(UnidadeFederacao.values());
	}
	
	@ModelAttribute("listaLogico")
	public List<TipoLogico> listaLogico(){
		return Arrays.asList(TipoLogico.values());
	}
	
}


//<td th:text="${#dates.format(item.dataVisita, 'dd/MM/yyyy')}"></td>



