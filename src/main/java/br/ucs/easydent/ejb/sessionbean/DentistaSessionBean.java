package br.ucs.easydent.ejb.sessionbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.app.exceptions.ProblemaPermissaoException;
import br.ucs.easydent.app.util.Util;
import br.ucs.easydent.ejb.session.DentistaSession;
import br.ucs.easydent.model.entity.Dentista;
import br.ucs.easydent.model.entity.Usuario;

@Stateless
public class DentistaSessionBean extends BaseSessionBean implements DentistaSession {

	public Dentista buscarPorId(Usuario usuario, Long id) {

		Dentista dentista = em.find(Dentista.class, id);
		detach(usuario, dentista);
		return dentista;
	}

	public List<Dentista> buscarTodos(Usuario usuario, Options options) throws ProblemaPermissaoException {

		StringBuilder queryString = new StringBuilder();
		Map<String, Object> queryParams = new HashMap<>();

		queryString.append("SELECT e FROM Dentista AS e");
		queryString.append(" WHERE 1=1 ");

		// Check permissions
		switch (usuario.getTipoUsuarioEnum()) {
		case ADMIN:
			// não precisa limitar, caso seja administrador
			break;

		case GERENTE:
		case SECRETARIA:
			queryString.append(" AND e.estabelecimento = :estabelecimentoUsuario ");
			queryParams.put("estabelecimentoUsuario", usuario.getEstabelecimento());

		case DENTISTA:
		case PACIENTE:
			throw new ProblemaPermissaoException();
		}

		if (options.getOrdenacao() != null) {
			queryString.append(" ORDER BY e." + options.getOrdenacao());
		}

		Query query = em.createQuery(queryString.toString());
		Util.checkPagination(query, options);

		List<Dentista> dentistas = (List<Dentista>) query.getResultList();
		detach(usuario, dentistas);

		return dentistas;
	}

	public Dentista salvar(Usuario usuario, Dentista entidade) {
		return em.merge(entidade);
	}

	public void excluir(Usuario usuario, Long id) {
		Dentista dentista = em.find(Dentista.class, id);
		if (dentista != null) {
			em.remove(dentista);
		}
	}

	public List<Dentista> buscarPorFiltro(Usuario usuario, BaseFilter<Dentista> filtro) {
		// TODO Criar método buscarPorFiltro em EntityEJB<Dentista>
		throw new NotImplementedException("DentistaSessionBean/buscarPorFiltro");
	}

	private void detach(Usuario usuario, List<Dentista> dentistas) {
		for (Dentista dentista : dentistas) {
			detach(usuario, dentista);
		}
	}

	private void detach(Usuario usuario, Dentista dentista) {
		if (dentista != null) {
			dentista.setEspecialidades(new ArrayList<>(dentista.getEspecialidades()));
		}
	}

}
