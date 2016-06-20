package br.ucs.easydent.ejb.sessionbean;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.app.util.Util;
import br.ucs.easydent.ejb.session.AgendaSession;
import br.ucs.easydent.model.entity.Agenda;
import br.ucs.easydent.model.entity.Consulta;
import br.ucs.easydent.model.entity.Usuario;

@Stateless
public class AgendaSessionBean extends BaseSessionBean implements AgendaSession {

	public Agenda buscarPorId(Usuario usuario, Long id) {
		return em.find(Agenda.class, id);
	}

	public List<Agenda> buscarTodos(Usuario usuario, Options params) {
		String queryString = "SELECT e FROM Agenda AS e";
		if (params.getOrdenacao() != null) {
			queryString += " ORDER BY e." + params.getOrdenacao();
		}

		Query query = em.createQuery(queryString);
		Util.checkPagination(query, params);

		List<Agenda> agendas = (List<Agenda>) query.getResultList();
		detach(agendas);

		return agendas;
	}

	public Agenda salvar(Usuario usuario, Agenda entidade) {
		return em.merge(entidade);
	}

	public void excluir(Usuario usuario, Long id) {
		Agenda agenda = this.buscarPorId(usuario, id);
		if (agenda != null) {
			em.remove(agenda);
		}
	}

	public List<Agenda> buscarPorFiltro(Usuario usuario, BaseFilter<Agenda> filtro) {
		// TODO Criar método buscarPorFiltro em EntityEJB<Agenda>
		return null;
	}

	private void detach(List<Agenda> agendas) {
		for (Agenda agenda : agendas) {
			detach(agenda);
		}
	}

	private void detach(Agenda agenda) {
		agenda.setHorarios(agenda.getHorarios());
		agenda.setHorariosEspeciais(agenda.getHorariosEspeciais());
	}

	@Override
	public List<Consulta> buscarConsultasPorData(Agenda agenda, Calendar inicio, Calendar fim, Options params) {

		Map<String, Object> _params = new HashMap<>();
		StringBuilder queryString = new StringBuilder();
		queryString.append(" SELECT e FROM Consulta AS e ");

		if (agenda == null) {
			throw new RuntimeException("Não é possível buscar consultas, sem especificar a agenda.");
		}

		queryString.append(" WHERE e.agenda = :agenda ");
		_params.put("agenda", agenda);

		if (inicio != null) {
			queryString.append(" AND e.data >= :inicio ");
			_params.put("inicio", inicio);
		}

		if (fim != null) {
			queryString.append(" AND e.data <= :fim ");
			_params.put("fim", fim);
		}

		Query query = em.createQuery(queryString.toString());
		Util.checkPagination(query, params);
		Util.setParams(query, _params);

		return (List<Consulta>) query.getResultList();
	}

}
