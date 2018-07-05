package br.itarocha.star;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.itarocha.star.model.Cidade;
import br.itarocha.star.model.Coordenada;
import br.itarocha.star.model.Cuspide;
import br.itarocha.star.model.ItemAspecto;
import br.itarocha.star.model.PlanetaPosicao;
import br.itarocha.star.model.TempoUniversal;

public class Mapa {
	private String nome;
	private String nomeCidade;
	
	private Calendar calendar;
	private TempoUniversal tu;
	private Coordenada latitude;
	private Coordenada longitude;
	private int fuso;
	private double sideralTime;
	private double deltaTSec;
	private double julDay;
	private int grausDefasagemAscendente = 0;
	
	private List<ItemAspecto> la = new ArrayList<ItemAspecto>();
	private List<Cuspide> cuspides = new ArrayList<Cuspide>();
	private List<PlanetaPosicao> posicoesPlanetas = new ArrayList<PlanetaPosicao>();
	
	public Mapa(String nome, String data, String hora, Cidade cidade) {
		this.nome = nome;
		this.tu = new TempoUniversal(data, hora, cidade.getFuso());
		
		calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		try {
			calendar.setTime(sdf.parse(data + " " + hora));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		make(nome, cidade.getFuso(), cidade.getLatitude(), cidade.getLongitude());
	}
	
	private void make(String nome, int fuso, String lat, String lon){
		int latdeg = 0, latmin = 0, latsec = 0;
		int londeg = 0, lonmin = 0, lonsec = 0;
        String[] slat = lat.split("\\.");
        try {
            latdeg = Integer.parseInt(slat[0]);
            latmin = Integer.parseInt(slat[1]);
            latsec = Integer.parseInt(slat[2]);
        }
        catch(Exception e) {
            //x = -999;  // TODO better error handling
        }
		
        String[] slon = lon.split("\\.");
        try {
            londeg = Integer.parseInt(slon[0]);
            lonmin = Integer.parseInt(slon[1]);
            lonsec = Integer.parseInt(slon[2]);
        }
        catch(Exception e) {
            //x = -999;  // TODO better error handling
        }
		this.latitude = new Coordenada(latdeg, latmin, latsec);
		this.longitude = new Coordenada(londeg, lonmin, lonsec);
		
		this.fuso = fuso;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeCidade() {
		return nomeCidade;
	}
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	
	public Calendar getCalendar(){
		return this.calendar;
	}
	
	public Coordenada getLatitude() {
		return this.latitude;
	}

	public Coordenada getLongitude() {
		return this.longitude;
	}

	public String getData() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(this.calendar.getTime());
	}

	public String getHora() {
		DateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(calendar.getTime());
	}

	public int getFuso() {
		return this.fuso;
	}

	public int getAnoUT(){
		return tu.getAno();
	}
	
	public int getMesUT(){
		return tu.getMes();
	}
	
	public int getDiaUT(){
		return tu.getDia();
	}
	public int getMinutoUT(){
		return tu.getMinuto();
	}
	
	public int getSegundoUT(){
		return tu.getSegundo();
	}
	
	public double getHoraDouble(){
		return tu.getHoraDouble();
	}

	public List<ItemAspecto> getListaAspectos(){
		return this.la;
	}
	
	public List<Cuspide> getListaCuspides(){
		return this.cuspides;
	}
	
	public List<PlanetaPosicao> getPosicoesPlanetas(){
		return this.posicoesPlanetas;
	}

	public double getSideralTime() {
		return sideralTime;
	}

	public void setSideralTime(double sideralTime) {
		this.sideralTime = sideralTime;
	}

	public double getJulDay() {
		return julDay;
	}

	public double getDeltaTSec() {
		return deltaTSec;
	}

	public void setDeltaTSec(double deltaTSec) {
		this.deltaTSec = deltaTSec;
	}

	public void setJulDay(double julDay) {
		this.julDay = julDay;
	}

	public int getGrausDefasagemAscendente() {
		return grausDefasagemAscendente;
	}

	public void setGrausDefasagemAscendente(int grausDefasagemAscendente) {
		this.grausDefasagemAscendente = grausDefasagemAscendente;
	}

}
