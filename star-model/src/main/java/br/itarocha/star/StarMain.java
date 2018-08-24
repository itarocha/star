package br.itarocha.star;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.itarocha.star.model.Cidade;

public class StarMain {

	//http://th-mack.de/international/download/index.html
	//http://www.radixpro.org/fix-east-west.html
	public static void main( String[] args) {
		// Rode com os seguintes argumentos
		// "Itamar Rocha" "29/06/1972" "05:00" "Caxias" "MA"
		
		String[] argumentos = new String[5];
		String nome;
		String data;
		String hora;
		String cidade;
		String uf;
		if (args.length < 5){
			System.out.println("Entre com os 5 parametros com espaco");
			System.out.println("Nome");
			System.out.println("Data de nascimento formato DD/MM/AAAA");
			System.out.println("Hora de nascimento qualquer formato com ou sem segundos com delimitador \".\" ou \":\" HH:MM OU HH.MM.SS");
			System.out.println("Cidade de nascimento");
			System.out.println("UF de nascimento");
			nome = "Itamar Rocha Chaves Junior";
			data = "29/06/1972";
			hora = "05:00";
			cidade = "Caxias";
			uf = "MA";
		} else {
			nome = args[0];
			data = args[1];
			hora = args[2];
			cidade = args[3];
			uf = args[4];
		}
		
		StarMain m = new StarMain();
		try {
			Mapa mapa = m.buildMapa(nome, data, hora, cidade, uf);
			if (mapa != null) {
	    		String json = new DecoradorMapa(mapa).getJSON();
	    		//Grafico.build(mapa);
	    		ChartPainter cp = new ChartPainter(mapa,".");
	    		System.out.println(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public StarMain(){}

	// ("Itamar","29/06/1972","5.0.0", Caxias, MA
	public Mapa buildMapa(String nome, String data, String hora, String cidade, String uf ) throws Exception{
		Mapa retorno = null;
		MapaBuilder construtor = MapaBuilder.getInstance(".");
		Cidade c = MapeadorCidades.getInstance().getCidade(cidade, uf);
		if (c != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date d = sdf.parse(data + " "+hora);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
    		retorno = construtor.build(nome, cal, cidade, uf);
		} else {
			System.out.println("Nao conseguiu localizar cidade");
		}
		return retorno;
	}
	
}
// Site Teoria da Conspiração - Link de mapa
// http://www.deldebbio.com.br/
// http://www.viraj.com.br/
// http://www.sadhana.com.br/cgi-local/mapas/mapanow.cgi

