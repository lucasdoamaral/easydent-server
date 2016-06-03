package br.ucs.easydent.sessionbean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;

import br.ucs.easydent.filtro.BaseFilter;
import br.ucs.easydent.session.EspecialidadeSession;
import br.ucs.easydent.util.Util;
import br.ucs.easydental.model.Especialidade;

@Stateless
public class EspecialidadeSessionBean extends BaseSessionBean implements EspecialidadeSession {

	public Especialidade buscarPorId(Long id) {
		return em.find(Especialidade.class, id);
	}

	public List<Especialidade> buscarTodos(QueryParams params) {
		
		String queryString = "SELECT e FROM Especialidade AS e";
		if (params.getOrdenacao() != null) {
			queryString += " ORDER BY e." + params.getOrdenacao();
		}
		
		Query query = em.createQuery(queryString);

		Util.checkPagination(query, params);
		
		return query.getResultList();
	}

	public Especialidade salvar(Especialidade entidade) {
		return em.merge(entidade);
	}

	public void excluir(Long id) {
		Especialidade especialidade = em.find(Especialidade.class, id);
		if (especialidade != null) {
			em.remove(especialidade);
		}
	}

	public List<Especialidade> buscarPorFiltro(BaseFilter<Especialidade> filtro) {
		// TODO Criar método buscarPorFiltro em EntityEJB<Especialidade>
		throw new NotImplementedException("EspecialidadeSessionBean/buscarPorFiltro");
	}

}
