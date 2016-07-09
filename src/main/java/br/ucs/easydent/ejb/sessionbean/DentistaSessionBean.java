package br.ucs.easydent.ejb.sessionbean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.app.exceptions.ProblemaPermissaoException;
import br.ucs.easydent.app.util.Util;
import br.ucs.easydent.ejb.session.ConsultaSession;
import br.ucs.easydent.ejb.session.DentistaSession;
import br.ucs.easydent.model.entity.Dentista;
import br.ucs.easydent.model.entity.Usuario;

@Stateless
public class DentistaSessionBean extends BaseSessionBean implements DentistaSession {

	private static final int INCREMENTO_CONSULTA = 15;

	@EJB
	private ConsultaSession consultaSession;

	public Dentista buscarPorId(Usuario usuario, Long id) {

		Dentista dentista = em.find(Dentista.class, id);
		detach(usuario, dentista);
		return dentista;
	}

	@Override
	public List<String> buscarHorariosDisponiveis(Usuario usuario, Dentista dentista, LocalDate data) {

		// {
		// // Check permissions
		// switch (usuario.getTipoUsuarioEnum()) {
		// case ADMIN:
		// break;
		//
		// // Se for secretaria
		// case GERENTE:
		// case SECRETARIA:
		// if
		// (!usuario.getEstabelecimento().equals(dentista.getEstabelecimento()))
		// {
		// throw new WebApplicationException(Status.UNAUTHORIZED);
		// }
		// break;
		//
		// case DENTISTA:
		// if (!usuario.equals(dentista.getUsuario())) {
		// throw new WebApplicationException(Status.UNAUTHORIZED);
		// }
		// break;
		//
		// case PACIENTE:
		// default:
		// throw new WebApplicationException(Status.UNAUTHORIZED);
		// }
		// }

		List<String> horariosDisponiveis = new ArrayList<>();

		LocalTime horaInicial = LocalTime.of(0, 0);
		LocalTime horaFinal = LocalTime.of(0, 0);

		do {
			horariosDisponiveis.add(horaInicial.format(DateTimeFormatter.ofPattern("HH:mm")));
			horaInicial = horaInicial.plusMinutes(INCREMENTO_CONSULTA);
		} while (!horaInicial.equals(horaFinal));

		// TODO Remover horários já ocupados.
		// Vou implementar isso futuramente, pois não causa tantos problemas já
		// que a disponibilidade do horário vai ser verificada no momento de
		// salvar a consulta

		// ConsultaFilter filtroConsulta = new ConsultaFilter();
		// filtroConsulta.setData(data);
		// List<Consulta> consultasDoDia =
		// consultaSession.buscarPorFiltro(usuario, new Options(),
		// filtroConsulta);

		// List<String> horariosOcupados
		// for (Consulta consulta : consultasDoDia) {
		//
		// }

		// for (HorarioDisponivel horario : horarios) {
		//
		// // Busca os horários daquele dia da semana
		// if
		// (horario.getDiaSemanaEnum().getId().equals(diaDaSemana.getValue())) {
		//
		// LocalTime horaInicial = horario.getHoraInicial();
		// LocalTime horaAlmocoInicial = horario.getHoraAlmocoInicial();
		//
		// while (horaInicial.isBefore(horaAlmocoInicial) &&
		// horaInicial.isBefore(horario.getHoraFinal())) {
		// horariosDisponiveis.add(horaInicial.format(new
		// DateTimeFormatterBuilder().toFormatter()));
		// horaInicial = horaInicial.plusMinutes(15);
		// }
		//
		// if (horario.getHoraAlmocoFinal() != null) {
		// horaInicial = horario.getHoraAlmocoFinal();
		// while (horaInicial.isBefore(horario.getHoraFinal())) {
		// horariosDisponiveis.add(horaInicial.format(new
		// DateTimeFormatterBuilder().toFormatter()));
		// horaInicial = horaInicial.plusMinutes(15);
		// }
		// }
		//
		// // LocalTime hora = LocalTime.of(horaInicial.get(Calendar.HOUR),
		// // horaInicial.get(Calendar.MINUTE));
		//
		// }
		// }

		return horariosDisponiveis;

	}

	public List<Dentista> buscarTodos(Usuario usuario, Options options) throws ProblemaPermissaoException {

		StringBuilder queryString = new StringBuilder();
		Map<String, Object> queryParams = new HashMap<>();

		queryString.append("SELECT e FROM Dentista AS e");
		queryString.append(" WHERE 1=1 ");

		// Check permissions
		switch (usuario.getTipoUsuarioEnum()) {
		case ADMIN:
			// não precisa limitar, caso seja administrador
			break;

		case GERENTE:
		case SECRETARIA:
			queryString.append(" AND e.estabelecimento = :estabelecimentoUsuario ");
			queryParams.put("estabelecimentoUsuario", usuario.getEstabelecimento());
			break;

		case DENTISTA:
		case PACIENTE:
			throw new ProblemaPermissaoException();
		}

		if (options.getOrdenacao() != null) {
			queryString.append(" ORDER BY e." + options.getOrdenacao());
		}

		Query query = em.createQuery(queryString.toString());
		Util.checkPagination(query, options);
		Util.setParams(query, queryParams);

		List<Dentista> dentistas = (List<Dentista>) query.getResultList();
		detach(usuario, dentistas);

		return dentistas;
	}

	public Dentista salvar(Usuario usuario, Dentista entidade) {

		// Criando dentista novo
		if (entidade.getId() == null) {
			entidade.setEstabelecimento(usuario.getEstabelecimento());
		}

		return em.merge(entidade);
	}

	public void excluir(Usuario usuario, Long id) {
		Dentista dentista = em.find(Dentista.class, id);
		if (dentista != null) {
			em.remove(dentista);
		}
	}

	public List<Dentista> buscarPorFiltro(Usuario usuario, Options options, BaseFilter<Dentista> filtro) {
		// TODO Criar método buscarPorFiltro em EntityEJB<Dentista>
		throw new NotImplementedException("DentistaSessionBean/buscarPorFiltro");
	}

	private void detach(Usuario usuario, List<Dentista> dentistas) {
		for (Dentista dentista : dentistas) {
			detach(usuario, dentista);
		}
	}

	private void detach(Usuario usuario, Dentista dentista) {
		if (dentista != null) {
			dentista.setEspecialidades(new ArrayList<>(dentista.getEspecialidades()));
			dentista.setHorarios(new ArrayList<>(dentista.getHorarios()));
			dentista.setHorariosEspeciais(new ArrayList<>(dentista.getHorariosEspeciais()));
		}
	}

}
