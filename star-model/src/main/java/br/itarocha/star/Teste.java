package br.itarocha.star;

import br.itarocha.star.model.Cidade;
import br.itarocha.star.model.TempoUniversal;

public class Teste {
	
	public static void main(String... args) throws Exception{
		TempoUniversal tu = new TempoUniversal("13/10/1994","18:35",-3);
		
		/*
		// Transforma 13/10/1994 18:35 em 13/10/1994 21:35
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Date d = sdf.parse("13/10/1994 18:35");
		int fuso = -3;
		TimeZone tmz = Funcoes.timeZone(fuso);
		GregorianCalendar c = new GregorianCalendar(tmz);
		c.setTime(d); 
		System.out.println(c.getTime());
		
		DateFormat pstFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		pstFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		System.out.println(pstFormat.format(c.getTime()));
		
		String dh = pstFormat.format(c.getTime());
		System.out.println(dh);
		
		
		Calendar c2 = Calendar.getInstance();
		c2.setTime(sdf.parse(dh));
		double dhour = c2.get(Calendar.HOUR_OF_DAY) + c.get(Calendar.MINUTE) / 60.0 + c.get(Calendar.SECOND) / 3600.0;
		*/
		
		System.out.println(tu.getAno());
		System.out.println(tu.getMes());
		System.out.println(tu.getDia());
		System.out.println(tu.getHora());
		System.out.println(tu.getMinuto());
		System.out.println(tu.getHoraDouble());
		//tjd := swe_julday(iyear, imonth, iday, dhour, 1);		
	}
	
	
	private void testarCidade() {
		MapeadorCidades m = MapeadorCidades.getInstance();
		Cidade c = null;
		
		String cidade = "Vilã Bela da Santíssimá Trindade";
		String uf = "MT";
		c = m.getCidade(cidade, uf);
		if (c != null) {
			System.out.println("FOUND CIDADE");
			System.out.println(c);
		} else {
			System.out.println("CIDADE NOT FOUND - \""+cidade+"\" \""+uf+"\"");
		}
	}

}

