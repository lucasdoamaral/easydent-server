package br.ucs.easydent.rest.services.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.ucs.easydent.app.dto.filtro.ConsultaFilter;
import br.ucs.easydent.app.exceptions.EasydentException;
import br.ucs.easydent.app.util.Util;
import br.ucs.easydent.ejb.session.ConsultaSession;
import br.ucs.easydent.ejb.session.EntityEJB;
import br.ucs.easydent.ejb.sessionbean.Options;
import br.ucs.easydent.model.entity.Consulta;
import br.ucs.easydent.model.enums.SituacaoConsultaEnum;
import br.ucs.easydent.rest.services.RestException;

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
			@PathParam("quantidade") Integer quantidade, @HeaderParam("X-Auth-Token") String token)
			throws RestException {

		Options parametros = new Options(quantidadeRegistros, primeiroRegistro, ordenacao);
		if (parametros.getQuantidadeRegistros() == null) {
			parametros.setQuantidadeRegistros(quantidade);
		}

		ConsultaFilter filtro = new ConsultaFilter();
		filtro.setDataMaiorDoQue(Calendar.getInstance());
		filtro.setSituacaoConsulta(SituacaoConsultaEnum.AGENDADA);

		try {
			return consultaSession.buscarPorFiltro(getUserFromToken(token), parametros, filtro);
		} catch (EasydentException e) {
			throw new RestException(e);
		}

	}

	@GET
	@Path("/ultimasnaorespondidas/{quantidade}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consulta> ultimasNaoRespondidas(@QueryParam("offset") Integer primeiroRegistro,
			@QueryParam("max-results") Integer quantidadeRegistros, @QueryParam("sort") String ordenacao,
			@PathParam("quantidade") Integer quantidade, @HeaderParam("X-Auth-Token") String token)
			throws RestException {

		Options parametros = new Options(quantidadeRegistros, primeiroRegistro, ordenacao);
		if (parametros.getQuantidadeRegistros() == null) {
			parametros.setQuantidadeRegistros(quantidade);
		}

		ConsultaFilter filtro = new ConsultaFilter();
		filtro.setDataMenorDoQue(Calendar.getInstance());
		filtro.setSituacaoConsulta(SituacaoConsultaEnum.AGENDADA);

		try {
			return consultaSession.buscarPorFiltro(getUserFromToken(token), parametros, filtro);
		} catch (EasydentException e) {
			throw new RestException(e);
		}

	}

	@GET
	@Path("/de/{anoInicial}/{mesInicial}/{diaInicial}/ate/{anoFinal}/{mesFinal}/{diaFinal}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consulta> buscarPorPeriodo(@HeaderParam("X-Auth-Token") String token,
			@PathParam("anoInicial") Integer anoInicial, @PathParam("mesInicial") Integer mesInicial,
			@PathParam("diaInicial") Integer diaInicial, @PathParam("anoFinal") Integer anoFinal,
			@PathParam("mesFinal") Integer mesFinal, @PathParam("diaFinal") Integer diaFinal) throws RestException {

		Calendar dataInicial = Util.newCalendar(anoInicial, mesInicial - 1, diaInicial);
		Util.beginOfTheDay(dataInicial);
		Calendar dataFinal = Util.newCalendar(anoFinal, mesFinal - 1, diaFinal);
		Util.endOfTheDay(dataFinal);

		ConsultaFilter filtro = new ConsultaFilter();
		filtro.setDataMaiorDoQue(dataInicial);
		filtro.setDataMenorDoQue(dataFinal);

		List<SituacaoConsultaEnum> situacoes = new ArrayList<>();
		situacoes.add(SituacaoConsultaEnum.AGENDADA);
		situacoes.add(SituacaoConsultaEnum.NAO_COMPARECIDA);
		situacoes.add(SituacaoConsultaEnum.ATENDIDA);
		filtro.setSituacoes(situacoes);

		try {
			return consultaSession.buscarPorFiltro(getUserFromToken(token), new Options(), filtro);
		} catch (EasydentException e) {
			throw new RestException(e);
		}
	}

	@OPTIONS
	public void options() {
		// System.out.println("Calling OPTIONS HTTP method.");
	}

}
