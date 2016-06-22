package br.ucs.easydent.ejb.sessionbean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.app.util.Util;
import br.ucs.easydent.ejb.session.TipoProcedimentoSession;
import br.ucs.easydent.model.entity.TipoProcedimento;
import br.ucs.easydent.model.entity.Usuario;

@Stateless
public class TipoProcedimentoSessionBean extends BaseSessionBean implements TipoProcedimentoSession {

	public TipoProcedimento buscarPorId(Usuario usuario, Long id) {
		return em.find(TipoProcedimento.class, id);
	}

	public List<TipoProcedimento> buscarTodos(Usuario usuario, Options params) {
		String queryString = "SELECT e FROM TipoProcedimento AS e";
		if (params.getOrdenacao() != null) {
			queryString += " ORDER BY e." + params.getOrdenacao();
		}

		Query query = em.createQuery(queryString);
		Util.checkPagination(query, params);

		return query.getResultList();
	}

	public TipoProcedimento salvar(Usuario usuario, TipoProcedimento entidade) {
		return em.merge(entidade);
	}

	public void excluir(Usuario usuario, Long id) {
		TipoProcedimento tipoProcedimento = em.find(TipoProcedimento.class, id);
		if (tipoProcedimento != null) {
			em.remove(tipoProcedimento);
		}
	}

	public List<TipoProcedimento> buscarPorFiltro(Usuario usuario, Options options,
			BaseFilter<TipoProcedimento> filtro) {
		// TODO Criar método buscarPorFiltro em TipoProcedimentoSessionBean
		throw new NotImplementedException("TipoProcedimentoSessionBean/buscarPorFiltro");
	}

}
