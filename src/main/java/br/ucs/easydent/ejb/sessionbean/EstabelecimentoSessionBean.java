package br.ucs.easydent.ejb.sessionbean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.app.util.Util;
import br.ucs.easydent.ejb.session.EstabelecimentoSession;
import br.ucs.easydent.model.entity.Estabelecimento;
import br.ucs.easydent.model.entity.Usuario;

@Stateless
public class EstabelecimentoSessionBean extends BaseSessionBean implements EstabelecimentoSession {

	public Estabelecimento buscarPorId(Usuario usuario, Long id) {
		return em.find(Estabelecimento.class, id);
	}

	public List<Estabelecimento> buscarTodos(Usuario usuario, Options params) {
		String queryString = "SELECT e FROM Estabelecimento AS e";
		if (params.getOrdenacao() != null) {
			queryString += " ORDER BY e." + params.getOrdenacao();
		}

		Query query = em.createQuery(queryString);
		Util.checkPagination(query, params);

		return (List<Estabelecimento>) query.getResultList();
	}

	public Estabelecimento salvar(Usuario usuario, Estabelecimento entidade) {
		return em.merge(entidade);
	}

	public void excluir(Usuario usuario, Long id) {
		Estabelecimento estabelecimento = em.find(Estabelecimento.class, id);
		if (estabelecimento != null) {
			em.remove(estabelecimento);
		}
	}

	public List<Estabelecimento> buscarPorFiltro(Usuario usuario, BaseFilter<Estabelecimento> filtro) {
		// TODO Criar método buscarPorFiltro em EstabelecimentoSessionBean
		throw new NotImplementedException("EstabelecimentoSessionBean/buscarPorFiltro");
	}

}
