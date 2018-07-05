package com.itarocha.starweb.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.itarocha.starweb.model.Casa;
import com.itarocha.starweb.model.Interpretacao;
import com.itarocha.starweb.model.MapaCuspide;
import com.itarocha.starweb.model.MapaPlanetaAspecto;
import com.itarocha.starweb.model.PlanetaCasa;
import com.itarocha.starweb.model.PlanetaSigno;
import com.itarocha.starweb.model.SignoSolar;
import com.itarocha.starweb.model.TipoPlaneta;
import com.itarocha.starweb.model.TipoSigno;
import com.itextpdf.io.font.FontConstants;
//import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import br.itarocha.star.Mapa;
import br.itarocha.star.model.Cuspide;
import br.itarocha.star.model.EnumAspecto;
import br.itarocha.star.model.EnumPlaneta;
import br.itarocha.star.model.ItemAspecto;
import br.itarocha.star.model.PlanetaAspecto;
import br.itarocha.star.model.PlanetaPosicao;

public class GeradorPdfService {
	
    public static final String FONT_ASTRO = "src/main/resources/fonts/AstroDotBasic.ttf";
	
	 public List<Interpretacao> createArquivo(boolean isTudo, MapaService servico, Mapa mapa, String texto) throws IOException {

		 List<Interpretacao> retorno = new LinkedList<Interpretacao>();
		 
		 //Map<String, Map<String, String>> mapTextos = new HashMap<>();
		 String NOT_FOUND = "<NOT_FOUND>";
		 Map<String, String> map = new LinkedHashMap<>();
		 String key = "";
		 
		 // SIGNO SOLAR
		 key = "";
		 for(PlanetaPosicao pp : mapa.getPosicoesPlanetas()){
			 
			 if(!"sol".equalsIgnoreCase(pp.getEnumPlaneta().getSigla())) continue;
			 
			 SignoSolar ss = servico.findSignoSolar("xx");
			 key = "O Signo Solar";

			 if (ss != null) {
				 if (isTudo || (!isTudo && !ss.getFoiConferido()) ) {
					 map.put(key, ss.getTexto());
					 retorno.add(this.tratarParagrafos(key, ss.getTexto()));
				 }
			 } else {
				 map.put(key, NOT_FOUND);
				 retorno.add(this.tratarParagrafos(key, NOT_FOUND));
			 }
			 
			 String signo = TipoSigno.getByString(pp.getEnumSigno().getSigla());
			 ss = servico.findSignoSolar(pp.getEnumSigno().getSigla());
			 key = String.format("%s", signo);
			 if (ss != null) {
				 if (isTudo || (!isTudo && !ss.getFoiConferido()) ) {
					 map.put(key, ss.getTexto());
					 retorno.add(this.tratarParagrafos(key, ss.getTexto()));
				 }
			 } else {
				 map.put(key, NOT_FOUND);
				 retorno.add(this.tratarParagrafos(key, NOT_FOUND));
			 }
			 break;
		 }
		 
		 // PLANETAS NOS SIGNOS
		 key = "";
		 for(PlanetaPosicao pp : mapa.getPosicoesPlanetas()){
			 
			 if("nor".equalsIgnoreCase(pp.getEnumPlaneta().getSigla() )) continue;
			 if("sol".equalsIgnoreCase(pp.getEnumPlaneta().getSigla() )) continue;
			 if("asc".equalsIgnoreCase(pp.getEnumPlaneta().getSigla() )) continue;
			 if("mce".equalsIgnoreCase(pp.getEnumPlaneta().getSigla() )) continue;

			 
			 // Apresentação
			 PlanetaSigno ps = servico.findPlanetaSigno(pp.getEnumPlaneta().getSigla(), pp.getEnumPlaneta().getSigla());
			 ps = servico.findPlanetaSigno(pp.getEnumPlaneta().getSigla(), "xx");
			 String planeta = TipoPlaneta.getByString(pp.getEnumPlaneta().getSigla());
			 key = String.format("%s nos Signos", planeta);
			 if (ps != null) {
				 if (isTudo || (!isTudo && !ps.getFoiConferido()) ) {
					 map.put(key, ps.getTexto());
					 retorno.add(this.tratarParagrafos(key, ps.getTexto()));
				 }
			 } else {
				 map.put(key, NOT_FOUND);
				 retorno.add(this.tratarParagrafos(key, NOT_FOUND));
			 }
			 
			 ps = servico.findPlanetaSigno(pp.getEnumPlaneta().getSigla(), pp.getEnumSigno().getSigla() );
			 //key = String.format("%s.%s", pp.getSiglaPlaneta(), pp.getNomeSigno());
			 planeta = TipoPlaneta.getByString(pp.getEnumPlaneta().getSigla());
			 String signo = TipoSigno.getByString(pp.getEnumSigno().getSigla());
			 key = String.format("%s em %s", planeta, signo);
			 if (ps != null) {
				 if (isTudo || (!isTudo && !ps.getFoiConferido()) ) {
					 map.put(key, ps.getTexto());
					 retorno.add(this.tratarParagrafos(key, ps.getTexto()));
				 }
			 } else {
				 if (!"sol".equalsIgnoreCase( pp.getEnumPlaneta().getSigla() ) ) {
					 map.put(key, NOT_FOUND);
					 retorno.add(this.tratarParagrafos(key, NOT_FOUND));
				 }
			 }
		 }

		 // CÚSPIDES

		 // CÚSPIDES - TÍTULO GERAL
		 key = "As Casas";
		 MapaCuspide mc = servico.findCuspide("xx", 0);
		 if (mc != null) {
			 map.put(key, mc.getTexto());
			 retorno.add(this.tratarParagrafos(key, mc.getTexto()));
		 } else {
			 map.put(key, NOT_FOUND);
			 retorno.add(this.tratarParagrafos(key, NOT_FOUND));
		 }
		 
		 for( Cuspide c : mapa.getListaCuspides()){
			 if( c.getNumero() > 12 ) continue;

			 // CÚSPIDES - TÍTULOS
			 //String casa = Casa.getByNumero(c.getNumero());
			 key = String.format("Casa %s", c.getNumero());
			 mc = servico.findCuspide("xx", c.getNumero());
			 if (mc != null) {
				 if (isTudo || (!isTudo && !mc.getFoiConferido()) ) {
					 map.put(key, mc.getTexto());
					 retorno.add(this.tratarParagrafos(key, mc.getTexto()));
				 }
			 } else {
				 map.put(key, NOT_FOUND);
				 retorno.add(this.tratarParagrafos(key, NOT_FOUND));
			 }
			 
			 String _key = String.format("%s.%02d", c.getEnumSigno().getSigla(), c.getNumero());
			 String signo = TipoSigno.getByString(c.getEnumSigno().getSigla());
			 String casa = Casa.getByNumero(c.getNumero());
			 key = String.format("%s na Cúspide da %s Casa", signo, casa);
			 mc = servico.findCuspide(c.getEnumSigno().getSigla(), c.getNumero());
			 if (mc != null) {
				 if (isTudo || (!isTudo && !mc.getFoiConferido()) ) {
					 map.put(key, mc.getTexto());
					 retorno.add(this.tratarParagrafos(key, mc.getTexto()));
				 }
			 } else {
				 map.put(key, NOT_FOUND);
				 retorno.add(this.tratarParagrafos(key, NOT_FOUND));
			 }
		}
		 
		// PLANETAS NAS CASAS
		
		for(PlanetaPosicao pp : mapa.getPosicoesPlanetas()){
			//PlanetaPosicao pp = mapa.getPosicoesPlanetas().get(i);
			 if("nor".equalsIgnoreCase(pp.getEnumPlaneta().getSigla())) continue;
			 if("asc".equalsIgnoreCase(pp.getEnumPlaneta().getSigla() )) continue;
			 if("mce".equalsIgnoreCase(pp.getEnumPlaneta().getSigla() )) continue;
		 
			 String planeta = TipoPlaneta.getByString(pp.getEnumPlaneta().getSigla());
			 String casa = Casa.getByNumero((int)pp.getCasa());

			 key = String.format("%s nas Casas", planeta);
			 PlanetaCasa pc = servico.findPlanetaCasa(pp.getEnumPlaneta().getSigla(), 0);
			 if (pc != null) {
				 if (isTudo || (!isTudo && !pc.getFoiConferido()) ) {
					 map.put(key, pc.getTexto());
					 retorno.add(this.tratarParagrafos(key, pc.getTexto()));
				 }
			 } else {
				 map.put(key, NOT_FOUND);
				 retorno.add(this.tratarParagrafos(key, NOT_FOUND));
			 }
			 
			 key = String.format("%s na %s Casa", planeta, casa);
			 pc = servico.findPlanetaCasa(pp.getEnumPlaneta().getSigla(), pp.getCasa());
			 if (pc != null) {
				 if (isTudo || (!isTudo && !pc.getFoiConferido()) ) {
					 map.put(key, pc.getTexto());
					 retorno.add(this.tratarParagrafos(key, pc.getTexto()));
				 }
			 } else {
				 map.put(key, NOT_FOUND);
				 retorno.add(this.tratarParagrafos(key, NOT_FOUND));
			 }
		}

		// ASPECTOS
		for(ItemAspecto ia : mapa.getListaAspectos() ){
			PlanetaAspecto pA = ia.getPlanetaA();
			PlanetaAspecto pB = ia.getPlanetaB(); 
			
			String planeta1 = TipoPlaneta.getByString(pA.getEnumPlaneta().getSigla());
			String planeta2 = TipoPlaneta.getByString(pB.getEnumPlaneta().getSigla());
			EnumAspecto aspecto = ia.getAspecto();
			
			key = String.format("%s em %s com %s", planeta1, aspecto.getNome(), planeta2 );
			MapaPlanetaAspecto a = servico.findAspecto(pA.getEnumPlaneta().getSigla(), pB.getEnumPlaneta().getSigla(), aspecto.getSigla() );
			 if (a != null) {
				 if (isTudo || (!isTudo && !a.getFoiConferido()) ) {
					 map.put(key, a.getTexto());
					 retorno.add(this.tratarParagrafos(key, a.getTexto()));
				 }
			 } else {
				 map.put(key, NOT_FOUND);
				 retorno.add(this.tratarParagrafos(key, NOT_FOUND));
			 }
		}
		
		for(String k : map.keySet()) {
			System.out.println(k + (NOT_FOUND.equals(map.get(k)) ? " - NÃO ENCONTRADO" : ""));
		}
		
		//////////////montarArquivoPdf(mapa, map);
		montarArquivoTxt(mapa, map);
		
		return retorno;
	 }

	 private Interpretacao tratarParagrafos(String titulo, String texto) {
		 List<String> textos = new LinkedList<String>();
		 String[] aaa = texto.split("\n\\s+");
		 for (int i = 0; i < aaa.length; i++ ) {
			 textos.add(aaa[i]);
		 }
		 return  new Interpretacao(titulo, textos);
	 }
	 
	 private List<String> tratarParagrafos(String texto){
		 List<String> retorno = new LinkedList<String>();
		 String[] aaa = texto.split("\n\\s+");
		 for (int i = 0; i < aaa.length; i++ ) {
			 retorno.add(aaa[i]);
		 }
		 return retorno;
	 }
	 
	 private void montarArquivoTxt(Mapa mapa, Map<String, String> map)  throws IOException {
		 String  nome = mapa.getNome().replaceAll(" ", "_").toLowerCase();
		 
		 String url = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		 String dest = String.format("%s/file_%s.txt",url,nome);		    
		 //String dest = String.format("d:/%s.txt",nome);
		 
		 FileWriter arq = new FileWriter(dest);
		 PrintWriter gravarArq = new PrintWriter(arq);
		 
		 for(String k : map.keySet()) {
			//Paragraph p = new Paragraph();
			//p.add(new Text(k)).setFontSize(14).setBold();
			// título
			gravarArq.printf(k+"\n");
			gravarArq.printf("---------------------------------------------------\n");
			///////////////document.add(p);
			 
			// Remover enter
			String texto = map.get(k);
			texto = texto.replace("\n", "");//.replace("\r", "");
			gravarArq.printf(texto);
			gravarArq.printf("\n\n");
			
			//p = new Paragraph();
			//p.add(new Text(texto).setFontSize(10) );
			
			///////////////document.add(p);
		 }
		 arq.close();
	 }
	 
	 private void montarArquivoPdf(Mapa mapa, Map<String, String> map) throws IOException {
		 String  nome = mapa.getNome().replaceAll(" ", "_").toLowerCase();

		 String url = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		 String dest = String.format("%s/file_%s.pdf",url,nome);		    
		 
		 PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
		 Document document = new Document(pdf);
		 document.setTextAlignment(TextAlignment.JUSTIFIED);
		 
		 PdfFont font = PdfFontFactory.createFont(FONT_ASTRO, true);
		 PdfFont bold = PdfFontFactory.createFont(FONT_ASTRO, true);
		 
		 PdfFont fontCourier = PdfFontFactory.createFont(FontConstants.COURIER);
		 
		 List<PlanetaPosicao> planetas = mapa.getPosicoesPlanetas();
		 
		 Paragraph pa = new Paragraph();
		 for(EnumPlaneta x : EnumPlaneta.values()) {
			 //System.out.println("buscando "+x.getId());
			 PlanetaPosicao pp = planetas.stream().filter(obj -> obj.getEnumPlaneta().getSigla().equals(x.getSigla())).findAny().orElse(null);
			 if (pp != null) {
				 
				 /////////EnumSigno eSigno = EnumSigno.getById(pp.getEnumSigno().getSigla());
				 //EnumPlaneta ePlaneta = EnumPlaneta.getBySigla(pp.getEnumPlaneta().getSigla());
				 
				 Text tLetraPlaneta = new Text(pp.getEnumPlaneta().getLetra()).setFontSize(12).setFont(font);
				 Text tLetraSigno = new Text(pp.getEnumSigno().getLetra()).setFontSize(12).setFont(font);
				 
				 String tmp = String.format(" %s°%s'%s\" casa %s ", pp.getGnc(), pp.getM(), pp.getS(), (int)pp.getCasa());
				 pa.add(tLetraPlaneta)
				 .add(new Text(" "+pp.getEnumPlaneta().getNome()+" ").setFontSize(8))
				 .add(tLetraSigno)
				 .add(new Text(tmp).setFontSize(8))
				 .add("\n");
			 }
		 }
		 document.add(pa);

		for(String k : map.keySet()) {
			//System.out.println(k + (NOT_FOUND.equals(map.get(k)) ? " - NÃO ENCONTRADO" : ""));
			Paragraph p = new Paragraph();
			p.add(new Text(k)).setFontSize(14).setBold();
			document.add(p);
			
			// Remover enter
			String texto = map.get(k);
			texto = texto.replace("\n", "");//.replace("\r", "");
			p = new Paragraph();
			p.add(new Text(texto).setFontSize(10) );
			
			document.add(p);
		}
		 
		 document.close();		 
		 
	 }
	
}
