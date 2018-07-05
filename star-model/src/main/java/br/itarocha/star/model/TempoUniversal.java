package br.itarocha.star.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import br.itarocha.star.util.Funcoes;

public class TempoUniversal{

	private int dia = 1;
	private int mes = 1;
	private int ano = 1980;
	private int hora = 0;
	private int minuto = 0;
	private int segundo = 0;
	private double horaDouble;
	
	public TempoUniversal() {
	}
	
	public TempoUniversal(String data, String hora, int fuso) {
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date d = new Date();
		try {
			d = sdf.parse(data + " " + hora);
		} catch(Exception e) {
			
		}

		TimeZone tmz = Funcoes.timeZone(fuso);
		GregorianCalendar c = new GregorianCalendar(tmz);
		c.setTime(d); 
		
		DateFormat pstFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		pstFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		String dh = pstFormat.format(c.getTime());
		System.out.println(dh);
		
		Calendar c2 = Calendar.getInstance();

		try {
			c2.setTime(sdf.parse(dh));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.dia = c2.get(Calendar.DAY_OF_MONTH);
		this.mes = c2.get(Calendar.MONTH) + 1;
		this.ano = c2.get(Calendar.YEAR);
		this.hora = c2.get(Calendar.HOUR_OF_DAY);
		//this.hora = c2.get(Calendar.HOUR);
		this.minuto = c2.get(Calendar.MINUTE);
		this.segundo = c2.get(Calendar.SECOND);
		
		this.horaDouble = this.hora + this.minuto / 60.0 + this.segundo / 3600.0;
		
		//tjd := swe_julday(iyear, imonth, iday, dhour, 1);		
	}

	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public int getAno() {
		return ano;
	}

	public int getHora() {
		return hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public int getSegundo() {
		return segundo;
	}

	public double getHoraDouble() {
		return horaDouble;
	} 
	
}
