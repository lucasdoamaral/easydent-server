package br.ucs.easydent.rest.services.entity;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.ucs.easydent.app.dto.filtro.ConsultaFilter;
import br.ucs.easydent.ejb.session.ConsultaSession;
import br.ucs.easydent.ejb.session.EntityEJB;
import br.ucs.easydent.ejb.sessionbean.Options;
import br.ucs.easydent.model.entity.Consulta;
import br.ucs.easydent.model.enums.SituacaoConsultaEnum;

@Path("/consultas")
public class ConsultaService extends EntityService<Consulta> {

	@EJB
	private ConsultaSession consultaSession;

	@Override
	protected EntityEJB<Consulta> getEJB() {
		return consultaSession;
	}

	@GET
	@Path("/proximas/{quantidade}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consulta> proximas(@QueryParam("offset") Integer primeiroRegistro,
			@QueryParam("max-results") Integer quantidadeRegistros, @QueryParam("sort") String ordenacao,
			@PathParam("quantidade") Integer quantidade, @HeaderParam("X-Auth-Token") String token) {

		Options parametros = new Options(quantidadeRegistros, primeiroRegistro, ordenacao);
		if (parametros.getQuantidadeRegistros() == null) {
			parametros.setQuantidadeRegistros(quantidade);
		}

		ConsultaFilter filtro = new ConsultaFilter();
		filtro.setDataMaiorDoQue(Calendar.getInstance());

		return consultaSession.buscarPorFiltro(getUserFromToken(token), parametros, filtro);

	}

	@GET
	@Path("/ultimasnaorespondidas/{quantidade}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consulta> ultimasNaoRespondidas(@QueryParam("offset") Integer primeiroRegistro,
			@QueryParam("max-results") Integer quantidadeRegistros, @QueryParam("sort") String ordenacao,
			@PathParam("quantidade") Integer quantidade, @HeaderParam("X-Auth-Token") String token) {

		Options parametros = new Options(quantidadeRegistros, primeiroRegistro, ordenacao);
		if (parametros.getQuantidadeRegistros() == null) {
			parametros.setQuantidadeRegistros(quantidade);
		}

		ConsultaFilter filtro = new ConsultaFilter();
		filtro.setDataMenorDoQue(Calendar.getInstance());
		filtro.setSituacaoConsulta(SituacaoConsultaEnum.AGENDADA);

		return consultaSession.buscarPorFiltro(getUserFromToken(token), parametros, filtro);

	}

	
}
