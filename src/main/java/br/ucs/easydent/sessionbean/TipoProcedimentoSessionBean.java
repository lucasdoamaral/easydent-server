package br.ucs.easydent.sessionbean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;

import br.ucs.easydent.filtro.BaseFilter;
import br.ucs.easydent.session.TipoProcedimentoSession;
import br.ucs.easydent.util.Util;
import br.ucs.easydental.model.TipoProcedimento;

@Stateless
public class TipoProcedimentoSessionBean extends BaseSessionBean implements TipoProcedimentoSession {

	public TipoProcedimento buscarPorId(Long id) {
		return em.find(TipoProcedimento.class, id);
	}

	public List<TipoProcedimento> buscarTodos(QueryParams params) {
		String queryString = "SELECT e FROM TipoProcedimento AS e";
		if (params.getOrdenacao()!=null){
			queryString += " ORDER BY e." + params.getOrdenacao();
		}
		
		Query query = em.createQuery(queryString);
		Util.checkPagination(query, params);
		
		return query.getResultList();
	}

	public TipoProcedimento salvar(TipoProcedimento entidade) {
		return em.merge(entidade);
	}

	public void excluir(Long id) {
		TipoProcedimento tipoProcedimento = em.find(TipoProcedimento.class, id);
		if (tipoProcedimento != null) {
			em.remove(tipoProcedimento);
		}
	}

	public List<TipoProcedimento> buscarPorFiltro(BaseFilter<TipoProcedimento> filtro) {
		// TODO Criar método buscarPorFiltro em TipoProcedimentoSessionBean
		throw new NotImplementedException("TipoProcedimentoSessionBean/buscarPorFiltro");
	}

}
