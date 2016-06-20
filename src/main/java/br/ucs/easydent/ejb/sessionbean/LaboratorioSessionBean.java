package br.ucs.easydent.ejb.sessionbean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.app.util.Util;
import br.ucs.easydent.ejb.session.LaboratorioSession;
import br.ucs.easydent.model.entity.Laboratorio;
import br.ucs.easydent.model.entity.Usuario;

@Stateless
public class LaboratorioSessionBean extends BaseSessionBean implements LaboratorioSession {

	@Override
	public Laboratorio buscarPorId(Usuario usuario, Long id) {
		return em.find(Laboratorio.class, id);
	}

	@Override
	public List<Laboratorio> buscarTodos(Usuario usuario, Options params) {
				
		String queryString = "SELECT e FROM Laboratorio AS e";
		
		if (params.getOrdenacao() != null) {
			queryString += "ORDER BY e." + params.getOrdenacao();
		}

		Query query = em.createQuery(queryString);
		Util.checkPagination(query, params);
		
		List<Laboratorio> laboratorios = (List<Laboratorio>)query.getResultList();
		detach(laboratorios);
		
		return laboratorios;
	}

	@Override
	public Laboratorio salvar(Usuario usuario, Laboratorio entidade) {
		// TODO Criar método salvar em EntityEJB<Laboratorio>
		return null;
	}

	@Override
	public void excluir(Usuario usuario, Long id) {
		// TODO Criar método excluir em EntityEJB<Laboratorio>

	}

	@Override
	public List<Laboratorio> buscarPorFiltro(Usuario usuario, BaseFilter<Laboratorio> filtro) {
		// TODO Criar método buscarPorFiltro em EntityEJB<Laboratorio>
		return null;
	}

	private void detach(List<Laboratorio> laboratorios) {
		for (Laboratorio laboratorio : laboratorios) {
			detach(laboratorio);
		}
	}

	private void detach(Laboratorio laboratorio) {
		// TODO implementar
	}

}
