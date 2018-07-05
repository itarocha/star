package com.itarocha.starweb;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import com.itarocha.starweb.model.MapaCuspide;
import com.itarocha.starweb.model.MapaPlanetaAspecto;
import com.itarocha.starweb.model.PlanetaCasa;
import com.itarocha.starweb.model.PlanetaSigno;
import com.itarocha.starweb.model.SignoSolar;
import com.itarocha.starweb.model.TipoPlaneta;
import com.itarocha.starweb.model.TipoSigno;
import com.itarocha.starweb.service.MapaService;



@SpringBootApplication
public class StarWebApplication {

	@Autowired 
	MapaService svc;

	public static void main(String[] args) {
		SpringApplication.run(StarWebApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver(){
		return new FixedLocaleResolver(new Locale("pt", "BR") );
	}
	
	@Configuration
	public static class MvcConfig extends WebMvcConfigurerAdapter{
		
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addRedirectViewController("/", "/mapa/new");
		}
	
	}
		
	//@PostConstruct
	private void testar() {
		SignoSolar ss = svc.findSignoSolar("ca");
		System.out.println("\n\n\n\n\n\n\n\n"+ss.getDescricao()+"\n"+ss.getTexto()+"\n\n\n\n\n\n\n\n");

		PlanetaSigno ps = svc.findPlanetaSigno("ven", "li");
		System.out.println("\n\n\n\n\n\n\n\n"+ps.getPlaneta() + " - " + ps.getSigno()+"\n"+ps.getTexto()+"\n\n\n\n\n\n\n\n");

		PlanetaCasa pc = svc.findPlanetaCasa("lua", 10);
		System.out.println("\n\n\n\n\n\n\n\n"+pc.getPlaneta() + " - " + 10 +"\n"+pc.getTexto()+"\n\n\n\n\n\n\n\n");
		
		//MapaPlanetaAspecto a = svc.findAspecto("sat", "sol", "op");
		MapaPlanetaAspecto a = svc.findAspecto("lua", "sul", "sx");
		if (a != null) {
			System.out.println("\n\n\n\n\n\n\n\n"+a.getPlanetaOrigem() + " - " + a.getAspecto() + " - "+ a.getPlanetaDestino() +"\n"+a.getTexto()+"\n\n\n\n\n\n\n\n");
		}
		
		MapaCuspide c = svc.findCuspide("ge", 1);
		if (c != null) {
			System.out.println("\n\n\n\n\n"+c.getSigno() + " - " + c.getCasa() +"\n"+c.getTexto()+"\n\n\n\n\n\n\n\n");
		} else {
			System.out.println("não encontrado GEMEOS NA CUSPIDE DA 1A CASA");
		}
	}
}
