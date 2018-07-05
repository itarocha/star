package com.itarocha.starweb.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itarocha.starweb.model.MapaCuspide;
import com.itarocha.starweb.model.MapaPlanetaAspecto;
import com.itarocha.starweb.model.PlanetaCasa;
import com.itarocha.starweb.model.PlanetaSigno;
import com.itarocha.starweb.model.SignoSolar;
import com.itarocha.starweb.model.TipoAspecto;
import com.itarocha.starweb.model.TipoPlaneta;
import com.itarocha.starweb.model.TipoRelacao;
import com.itarocha.starweb.model.TipoSigno;

@Service
public class MapaService {
	
	@Autowired
	private EntityManager em;

	public SignoSolar findSignoSolar(String signo) {
		TipoSigno ts = getTipoSigno(signo);
		Query q = em.createQuery("SELECT o FROM SignoSolar o where o.signo = :SIGNO")
					.setParameter("SIGNO", ts);
		SignoSolar model = null;
		try {
			model = (SignoSolar) q.getSingleResult();
		} catch(NoResultException e) {
		} 
		return model;
	}
	
	public MapaCuspide findCuspide(String signo, Integer casa) {
		TipoSigno ts = getTipoSigno(signo);
		Query q = em.createQuery("SELECT c FROM MapaCuspide c where c.signo = :SIGNO AND c.casa = :CASA")
					.setParameter("SIGNO", ts)
					.setParameter("CASA", casa);
		MapaCuspide model = null;
		try {
			model = (MapaCuspide) q.getSingleResult();
		} catch(NoResultException e) {
		} 
		return model;
	}

	public PlanetaSigno findPlanetaSigno(String planeta, String signo) {
		TipoPlaneta tp = getTipoPlaneta(planeta);
		TipoSigno ts = getTipoSigno(signo);
		Query q = em.createQuery("SELECT p FROM PlanetaSigno p where p.planeta = :PLANETA AND p.signo = :SIGNO")
					.setParameter("PLANETA", tp)
					.setParameter("SIGNO", ts);
		PlanetaSigno model = null;
		try {
			model = (PlanetaSigno) q.getSingleResult();
		} catch(NoResultException e) {
		} 
		return model;
	}
	
	public PlanetaCasa findPlanetaCasa(String planeta, Integer casa) {
		TipoPlaneta tp = getTipoPlaneta(planeta);
		Query q = em.createQuery("SELECT pc FROM PlanetaCasa pc where pc.planeta = :PLANETA AND pc.casa = :CASA")
					.setParameter("PLANETA", tp)
					.setParameter("CASA", casa);
		PlanetaCasa model = null;
		try {
			model = (PlanetaCasa) q.getSingleResult();
		} catch(NoResultException e) {
		} 
		return model;
	}

	public MapaPlanetaAspecto findAspecto(String planetaOrigem, String planetaDestino, String aspecto) {
		TipoPlaneta tpo = getTipoPlaneta(planetaOrigem);
		TipoPlaneta tpd = getTipoPlaneta(planetaDestino);
		TipoAspecto ta = getTipoAspecto(aspecto);
		Query q = em.createQuery("SELECT a FROM MapaPlanetaAspecto a "
								+ "WHERE ((a.planetaOrigem = :PO AND a.planetaDestino = :PD) "
								+ "OR (a.planetaOrigem = :PD AND a.planetaDestino = :PO)) "
								+ "AND a.aspecto = :ASP")
					.setParameter("PO", tpo)
					.setParameter("PD", tpd)
					.setParameter("ASP", ta);
		MapaPlanetaAspecto model = null;
		try {
			model = (MapaPlanetaAspecto) q.getSingleResult();
			
			if (TipoRelacao.DETALHE.equals(model.getTipoRelacao())) {
				MapaPlanetaAspecto mestre  = model.getAspectoMestre();
				if (mestre != null) {
					model = mestre; 
				}
			}
		} catch(NoResultException e) {
		}
		return model;
	}

	private TipoAspecto getTipoAspecto(String aspecto) {
		TipoAspecto ta = null; 
		try {
			ta = TipoAspecto.valueOf(aspecto.toUpperCase());
		} catch(Exception e) {
			ta = TipoAspecto.XX;
		}
		return ta;
	}

	private TipoSigno getTipoSigno(String signo) {
		TipoSigno ts = null; 
		try {
			ts = TipoSigno.valueOf(signo.toUpperCase());
		} catch(Exception e) {
			ts = TipoSigno.XX;
		}
		return ts;
	}

	private TipoPlaneta getTipoPlaneta(String planeta) {
		TipoPlaneta tp = null; 
		try {
			tp = TipoPlaneta.valueOf(planeta.toUpperCase());
		} catch(Exception e) {
			tp = TipoPlaneta.XXX;
		}
		return tp;
	}
	
	
	
	/*
	@Autowired
	private ClienteRepository clientes;
	
	@Autowired
	private ConsultaRepository consultas;
	
 	public void gravar(Consulta model, Cliente cliente){
		try{
			//BigDecimal altura = new BigDecimal(model.getCliente().getAltura()).divide(new BigDecimal(100),2,RoundingMode.UP);
			//BigDecimal altura = model.getCliente().getAlturaMetros();
			//BigDecimal imc = Calculadora.imc(model.getPeso(), altura);
			//System.out.println(model.getPeso());
			//System.out.println(cliente.getAlturaMetros() );
			//System.out.println(imc);
			
			BigDecimal imc = Calculadora.imc(model.getPeso(), cliente.getAlturaMetros() );
			
			//BigDecimal altura = new BigDecimal(model.getAltura()); //.divide(100,2,RoundingMode.UP);
			
			//new BigDecimal(model.getAltura()).divide(new BigDecimal(2), )
			
			//System.out.println(model.getAltura().divide(new BigDecimal(2)));
			
			model.setIndiceMassaCorporal( imc );			
			consultas.save(model);
		}catch(Exception e){
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	public void excluir(Long codigo) {
		consultas.delete(codigo);
	}

	public Consulta novaConsulta(NovaConsulta model) throws Exception{
		Cliente cli = clientes.getOne(model.getId());
		Consulta cons = new Consulta();
		cons.setCliente(cli);
		cons.setDataAgenda(model.getDataAgenda());
		cons.setAltura(cli.getAltura());
		cons.setRealizada("N");
		consultas.save(cons);
		return cons;
	}
	
	public List<Consulta> buscarConsultasPorCliente(Cliente cliente) {
		List<Consulta> lista = consultas.findByClienteOrderByDataConsultaDesc(cliente);
		return lista;
	}
	
	public Consulta findById(Long id) {
		return consultas.findOne(id);
	}
	
/*
@SqlResultSetMapping(
        name = "AuthorValueMapping",
        classes = @ConstructorResult(
                targetClass = AuthorValue.class,
                columns = {
                    @ColumnResult(name = "id", type = Long.class),
                    @ColumnResult(name = "firstname"),
                    @ColumnResult(name = "lastname"),
					@ColumnResult(name = "numBooks", type = Long.class)}))	 
	 
Query q = em.createNativeQuery("SELECT a.id, a.firstname, a.lastname, count(b.id) as numBooks 
FROM Author a JOIN BookAuthor ba on a.id = ba.authorid 
JOIN Book b ON b.id = ba.bookid 
GROUP BY a.id", "AuthorValueMapping");
List<AuthorValue> authors = q.getResultList();
 
for (AuthorValue a : authors) {
    System.out.println("Author "
            + a.getFirstName()
            + " "
            + a.getLastName()
            + " wrote "
            + a.getNumBooks()
            + " books.");
}	  
	  
	  
	 * */
	/*
	// https://www.thoughts-on-java.org/result-set-mapping-constructor-result-mappings/
	public List<ConsultaGrafico> getGrafico(Cliente cliente){
		Query q = em.createNativeQuery(	"SELECT c.id "+
										", c.data_agenda as dataAgenda "+
										", c.data_consulta as dataConsulta "+
										", c.realizada "+
										", c.peso "+
										", c.indice_massa_corporal as indiceMassaCorporal "+
										", c.pct_gordura_visceral as pctGorduraVisceral "+
										", c.pct_gordura_corporal as pctGorduraCorporal "+
										", c.pct_muscular as pctMuscular "+
										"FROM consulta c "+
										"WHERE c.cliente_id = :id "+
										"AND c.realizada = :realizada "+
										"ORDER BY c.data_consulta", "ConsultaGraficoMapping");
		q.setParameter("id", cliente.getId());
		q.setParameter("realizada", "S");
		List<ConsultaGrafico> lista = q.getResultList();
		return lista;
	}

	public List<ConsultaAgenda> getAgenda(Date dataIni, Date dataFim){
		Query q = em.createNativeQuery(	
				"SELECT     c.id " + 
				"         , COALESCE(c.data_consulta, c.data_agenda)  AS data " + 
				"         , c.data_agenda AS dataAgenda " + 
				"         , c.data_consulta AS dataConsulta " + 
				"         , c.realizada " + 
				"         , c.cliente_id AS clienteId " + 
				"	      , cli.nome " + 
				"	      , cli.telefone " + 
				"FROM       consulta c " + 
				"INNER JOIN cliente cli " + 
				"ON         cli.id = c.cliente_id " + 
				"WHERE COALESCE(c.data_consulta, c.data_agenda) BETWEEN :dataIni AND :dataFim " + 
				"ORDER BY   2", 
				"ConsultaAgendaMapping");
		q.setParameter("dataIni", dataIni);
		q.setParameter("dataFim", dataFim);
		List<ConsultaAgenda> lista = q.getResultList();
		return lista;
	}
	
/*	
BUSCA CONSULTAS BASEADAS NUM PERÍODO

SELECT     c.id
         , COALESCE(c.data_consulta, c.data_agenda)  AS data
         , c.data_agenda AS dataAgenda
         , c.data_consulta AS dataConsulta
         , c.realizada
         , c.cliente_id AS clienteId
	     , cli.nome
FROM       consulta c
INNER JOIN cliente cli
ON         cli.id = c.cliente_id
WHERE COALESCE(c.data_consulta, c.data_agenda) BETWEEN '2017-10-01' AND '2017-10-30'
ORDER BY   1
	
*/
}

