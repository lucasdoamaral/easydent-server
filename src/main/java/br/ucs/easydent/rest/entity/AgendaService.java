package br.ucs.easydent.rest.entity;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import br.ucs.easydent.session.AgendaSession;
import br.ucs.easydent.session.EntityEJB;
import br.ucs.easydental.model.Agenda;
import br.ucs.easydental.model.Consulta;

@Path("/agendas")
public class AgendaService extends EntityService<Agenda> {

	@EJB
	private AgendaSession agendaSession;

	@Override
	protected EntityEJB<Agenda> getEJB() {
		return agendaSession;
	}
	
	@GET
	@Path("/{agendaId}/consultas")
	public List<Consulta> buscarConsultas(@QueryParam("offset") Integer primeiroRegistro, @QueryParam("max-results") Integer quantidadeRegistros,
			@QueryParam("sort") String ordenacao, @QueryParam("data-inicial") Date dataInicial, @QueryParam("data-final") Date dataFinal, @PathParam("agendaId") Long agendaId ){
		return null;
	}


}
