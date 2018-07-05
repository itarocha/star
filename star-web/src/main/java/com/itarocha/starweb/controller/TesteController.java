package com.itarocha.starweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
import br.itarocha.star.DecoradorMapa;
import br.itarocha.star.Mapa;
import br.itarocha.star.MapaBuilder;
*/

@RestController
@RequestMapping("teste")
public class TesteController {

	/*
	@GetMapping()
	public String teste()
	{
		String nome = "Itamar Rocha Chaves Junior";
		String data = "29/06/1972";
		String hora = "05:00";
		String cidade = "Caxias";
		String uf = "MA";
		try {
			MapaBuilder builder = MapaBuilder.getInstance();
			Mapa mapa = builder.build(nome, data, hora, cidade, uf);
			if (mapa != null) {
				String json = new DecoradorMapa(mapa).getJSON();
				System.out.println(json);
				return json;
			}	
		} catch (Exception e) {
			
		}
		return "suuuuuuuu ceeee sssuu";
	}
	*/
}


		
