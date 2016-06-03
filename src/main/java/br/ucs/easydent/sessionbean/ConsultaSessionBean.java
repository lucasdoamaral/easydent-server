package br.ucs.easydent.sessionbean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;

import br.ucs.easydent.filtro.BaseFilter;
import br.ucs.easydent.session.ConsultaSession;
import br.ucs.easydent.util.Util;
import br.ucs.easydental.model.Consulta;
import br.ucs.easydental.model.Dentista;
import br.ucs.easydental.model.Especialidade;

@Stateless
public class ConsultaSessionBean extends BaseSessionBean implements ConsultaSession {

	public Consulta buscarPorId(Long id) {
		Consulta consulta = em.find(Consulta.class, id);
		detach(consulta);
		return consulta;
	}

	public List<Consulta> buscarTodos(QueryParams params) {
		String queryString = "SELECT e FROM Consulta AS e";
		if (params.getOrdenacao() != null) {
			queryString += " ORDER BY e." + params.getOrdenacao();
		}
		
		Query query = em.createQuery(queryString);
		Util.checkPagination(query, params);
		
		List<Consulta> consultas = (List<Consulta>) query.getResultList();
		detach(consultas);
		
		return consultas;
	}

	public Consulta salvar(Consulta entidade) {
		return em.merge(entidade);
	}

	public void excluir(Long id) {
		Consulta consulta = this.buscarPorId(id);
		if (consulta != null) {
			em.remove(consulta);
		}
	}

	public List<Consulta> buscarPorFiltro(BaseFilter<Consulta> filtro) {
		// TODO Criar método buscarPorFiltro em EntityEJB<Consulta>
		throw new NotImplementedException("ConsultaSessionBean/buscarPorFiltro");
	}

	private void detach(List<Consulta> consultas) {
		for (Consulta consulta : consultas) {
			detach(consulta);
		}
	}

	private void detach(Consulta consulta) {
		Dentista dentista = consulta.getDentista();
		dentista.setEspecialidades(new ArrayList<Especialidade>(dentista.getEspecialidades()));
		consulta.setDentista(dentista);
	}

	@Override
	public List<Consulta> buscarProximasConsultas(Dentista dentista, Integer proximas) {
		
		StringBuilder queryString = new StringBuilder();
		Map<String, Object> params = new HashMap<>();
		
		queryString.append(" SELECT e FROM Consulta AS e ");
		queryString.append(" WHERE e.data > :agora ");
		
		params.put("agora", Calendar.getInstance());
		
		if (dentista!= null) {
			queryString.append(" AND e.dentista = :dentista ");
			params.put("dentista", dentista);
		}
		
		queryString.append(" LIMIT " + proximas);
		
		Query query = em.createQuery(queryString.toString());
		Util.setParams(query, params);
		
		return query.getResultList();
		
	}

	@Override
	public List<Consulta> buscarProximasConsultas(Integer proximas) {
		return buscarProximasConsultas(null, proximas);
	}

}
