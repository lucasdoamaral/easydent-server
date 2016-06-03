package br.ucs.easydent.sessionbean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;

import br.ucs.easydent.filtro.BaseFilter;
import br.ucs.easydent.session.DentistaSession;
import br.ucs.easydent.util.Util;
import br.ucs.easydental.model.Dentista;

@Stateless
public class DentistaSessionBean extends BaseSessionBean implements DentistaSession {

	public Dentista buscarPorId(Long id) {

		Dentista dentista = em.find(Dentista.class, id);
		detach(dentista);
		return dentista;
	}

	public List<Dentista> buscarTodos(QueryParams params) {
		
		String queryString = "SELECT e FROM Dentista AS e";
		if (params.getOrdenacao() != null) {
			queryString += " ORDER BY e." + params.getOrdenacao();
		}

		Query query = em.createQuery(queryString);
		Util.checkPagination(query, params);
		
		List<Dentista> dentistas = query.getResultList();
		detach(dentistas);
		
		return dentistas;
	}

	public Dentista salvar(Dentista entidade) {
		return em.merge(entidade);
	}

	public void excluir(Long id) {
		Dentista dentista = em.find(Dentista.class, id);
		if (dentista != null) {
			em.remove(dentista);
		}
	}

	public List<Dentista> buscarPorFiltro(BaseFilter<Dentista> filtro) {
		// TODO Criar método buscarPorFiltro em EntityEJB<Dentista>
		throw new NotImplementedException("DentistaSessionBean/buscarPorFiltro");
	}

	private void detach(List<Dentista> dentistas) {
		for (Dentista dentista : dentistas) {
			detach(dentista);
		}
	}

	private void detach(Dentista dentista) {
		if (dentista != null) {
			dentista.setEspecialidades(new ArrayList<>(dentista.getEspecialidades()));
		}
	}

}
