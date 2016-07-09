package br.ucs.easydent.rest.services.entity;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.ucs.easydent.app.dto.filtro.ConsultaFilter;
import br.ucs.easydent.app.exceptions.ProblemaPermissaoException;
import br.ucs.easydent.app.exceptions.RegistroNaoEncontradoException;
import br.ucs.easydent.app.util.Util;
import br.ucs.easydent.ejb.session.ConsultaSession;
import br.ucs.easydent.ejb.session.DentistaSession;
import br.ucs.easydent.ejb.session.EntityEJB;
import br.ucs.easydent.ejb.sessionbean.Options;
import br.ucs.easydent.model.entity.Consulta;
import br.ucs.easydent.model.entity.Dentista;
import br.ucs.easydent.rest.services.RestException;

@Path("/dentistas")
public class DentistaService extends EntityService<Dentista> {

	@EJB
	private DentistaSession dentistaSession;

	@EJB
	private ConsultaSession consultaSession;

	@Override
	protected EntityEJB<Dentista> getEJB() {
		return dentistaSession;
	}

	@GET
	@Path("/{dentistaId}/horariosdisponiveis/{ano}/{mes}/{dia}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> buscarHorariosDisponiveis(@PathParam("ano") Integer ano, @PathParam("mes") Integer mes,
			@PathParam("dia") Integer dia, @HeaderParam("X-Auth-Token") String token,
			@PathParam("dentistaId") Long dentistaId) {

		Dentista dentista = null;
		// try {
		// dentista = dentistaSession.buscarPorId(getUserFromToken(token),
		// dentistaId);
		// } catch (ProblemaPermissaoException e) {
		// throw new RestException(e);
		// } catch (RegistroNaoEncontradoException e) {
		// throw new RestException(e);
		// }

		LocalDate data = LocalDate.of(ano, mes, dia);
		return dentistaSession.buscarHorariosDisponiveis(getUserFromToken(token), dentista, data);

	}

	@Path("/{dentistaId}/{anoInicial}/{mesInicial}/{diaInicial}/ate/{anoFinal}/{mesFinal}/{diaFinal}")
	public List<Consulta> buscarPorPeriodo(@HeaderParam("X-Auth-Token") String token,
			@PathParam("dentistaId") Long dentistaId, @PathParam("anoInicial") Integer anoInicial,
			@PathParam("mesInicial") Integer mesInicial, @PathParam("diaInicial") Integer diaInicial,
			@PathParam("anoFinal") Integer anoFinal, @PathParam("mesFinal") Integer mesFinal,
			@PathParam("diaFinal") Integer diaFinal) {

		Calendar dataInicial = Util.newCalendar(anoInicial, mesInicial, diaInicial);
		Util.beginOfTheDay(dataInicial);
		Calendar dataFinal = Util.newCalendar(anoFinal, mesFinal, diaFinal);
		Util.endOfTheDay(dataFinal);

		Dentista dentista;
		try {
			dentista = dentistaSession.buscarPorId(getUserFromToken(token), dentistaId);
		} catch (ProblemaPermissaoException e) {
			throw new RestException(e);
		} catch (RegistroNaoEncontradoException e) {
			throw new RestException(e);
		}

		ConsultaFilter filtro = new ConsultaFilter();
		filtro.setDentista(dentista);
		filtro.setDataMaiorDoQue(dataInicial);
		filtro.setDataMenorDoQue(dataFinal);

		try {
			return consultaSession.buscarPorFiltro(getUserFromToken(token), new Options(), filtro);
		} catch (ProblemaPermissaoException e) {
			throw new RestException(e);
		}
	}

	@OPTIONS
	public void options() {
		// System.out.println("Calling OPTIONS HTTP method.");
	}

}
