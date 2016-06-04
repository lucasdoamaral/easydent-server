package br.ucs.easydent.rest.services.entity;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.ucs.easydent.ejb.session.ConsultaSession;
import br.ucs.easydent.ejb.session.EntityEJB;
import br.ucs.easydent.model.entity.Consulta;
import br.ucs.easydent.model.entity.Dentista;

@Path("/consultas")
public class ConsultaService extends EntityService<Consulta> {

	@EJB
	private ConsultaSession consultaSession;

	@Override
	protected EntityEJB<Consulta> getEJB() {
		return consultaSession;
	}

	@GET
	@Path("/{dentista}/{quantidade}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consulta> proximas(@PathParam("quantidade") Integer quantidade, @PathParam("dentista") Long dentistaId) {

		Dentista dentista = new Dentista();
		dentista.setId(dentistaId);

		return consultaSession.buscarProximasConsultas(dentista, quantidade);

	}

}
