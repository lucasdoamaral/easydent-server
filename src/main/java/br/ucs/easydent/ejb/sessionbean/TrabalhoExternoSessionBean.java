package br.ucs.easydent.ejb.sessionbean;

import java.util.List;

import javax.persistence.Query;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.app.util.Util;
import br.ucs.easydent.ejb.session.TrabalhoExternoSession;
import br.ucs.easydent.model.entity.TrabalhoExterno;
import br.ucs.easydent.model.entity.Usuario;

public class TrabalhoExternoSessionBean extends BaseSessionBean implements TrabalhoExternoSession {

	@Override
	public TrabalhoExterno buscarPorId(Usuario usuario, Long id) {
		TrabalhoExterno trabalhoExterno = em.find(TrabalhoExterno.class, id);
		detach(trabalhoExterno);
		return trabalhoExterno;
	}

	@Override
	public List<TrabalhoExterno> buscarTodos(Usuario usuario, Options params) {
		String queryString = "SELECT e FROM TrabalhoExterno AS e";
		if (params.getOrdenacao() != null) {
			queryString += " ORDER BY e." + params.getOrdenacao();
		}
		
		Query query = em.createQuery(queryString);
		Util.checkPagination(query, params);
		
		List<TrabalhoExterno> trabalhosExternos = (List<TrabalhoExterno>) query.getResultList();
		detach(trabalhosExternos);
		
		return trabalhosExternos;
	}

	@Override
	public TrabalhoExterno salvar(Usuario usuario, TrabalhoExterno entidade) {
		return em.merge(entidade);
	}

	@Override
	public void excluir(Usuario usuario, Long id) {
		TrabalhoExterno trabalhoExterno = buscarPorId(usuario, id);
		em.remove(trabalhoExterno);
	}

	@Override
	public List<TrabalhoExterno> buscarPorFiltro(Usuario usuario, BaseFilter<TrabalhoExterno> filtro) {
		// TODO Criar método buscarPorFiltro em TrabalhoExternoSessionBean
		return null;
	}

	private void detach(List<TrabalhoExterno> trabalhosExternos) {
		for (TrabalhoExterno trabalhoExterno : trabalhosExternos) {
			detach(trabalhoExterno);
		}
	}

	private void detach(TrabalhoExterno trabalhoExterno) {
		// nada a fazer
	}

}
