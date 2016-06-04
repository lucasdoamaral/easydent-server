package br.ucs.easydent.ejb.sessionbean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.app.util.Util;
import br.ucs.easydent.ejb.session.EnderecoSession;
import br.ucs.easydent.model.entity.Endereco;

@Stateless
public class EnderecoSessionBean extends BaseSessionBean implements EnderecoSession {

	public Endereco buscarPorId(Long id) {
		return em.find(Endereco.class, id);
	}

	public List<Endereco> buscarTodos(QueryParams params) {
		String queryString = "SELECT e FROM Endereco AS e";
		if (params.getOrdenacao() != null) {
			queryString += " ORDER BY e." + params.getOrdenacao();
		}
		
		Query query = em.createQuery(queryString);
		Util.checkPagination(query, params);
		
		return (List<Endereco>) query.getResultList();
	}

	public Endereco salvar(Endereco entidade) {
		return em.merge(entidade);
	}

	public void excluir(Long id) {
		Endereco endereco = em.find(Endereco.class, id);
		if (endereco != null) {
			em.remove(endereco);
		}
	}

	public List<Endereco> buscarPorFiltro(BaseFilter<Endereco> filtro) {
		// TODO Criar método buscarPorFiltro em EnderecoSessionBean
		throw new NotImplementedException("EnderecoSessionBean/buscarPorFiltro");
	}

}
