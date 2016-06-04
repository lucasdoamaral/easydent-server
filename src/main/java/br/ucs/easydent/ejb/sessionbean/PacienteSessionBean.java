package br.ucs.easydent.ejb.sessionbean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.app.util.Util;
import br.ucs.easydent.ejb.session.PacienteSession;
import br.ucs.easydent.model.entity.Paciente;

@Stateless
public class PacienteSessionBean extends BaseSessionBean implements PacienteSession {

	public Paciente buscarPorId(Long id) {
		return em.find(Paciente.class, id);
	}

	public List<Paciente> buscarTodos(QueryParams params) {
		String queryString = "SELECT e FROM Paciente AS e";
		if (params.getOrdenacao()!=null) {
			queryString += " ORDER BY e." + params.getOrdenacao();
		}
		
		Query query = em.createQuery(queryString);
		Util.checkPagination(query, params);
		
		return query.getResultList();
	}

	public Paciente salvar(Paciente entidade) {
		return em.merge(entidade);
	}

	public void excluir(Long id) {
		Paciente paciente = em.find(Paciente.class, id);
		if (paciente != null) {
			em.remove(paciente);
		}
	}

	public List<Paciente> buscarPorFiltro(BaseFilter<Paciente> filtro) {
		// TODO Criar método buscarPorFiltro em PacienteSessionBean
		throw new NotImplementedException("PacienteSessionBean/buscarPorFiltro");
	}
	
}
