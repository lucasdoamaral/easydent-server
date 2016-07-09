package br.ucs.easydent.ejb.sessionbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.app.exceptions.ProblemaPermissaoException;
import br.ucs.easydent.app.util.Util;
import br.ucs.easydent.ejb.session.LaboratorioSession;
import br.ucs.easydent.model.entity.Laboratorio;
import br.ucs.easydent.model.entity.Usuario;
import br.ucs.easydent.security.PermissaoPorEstabelecimento;
import br.ucs.easydent.security.Permission;

@Stateless
public class LaboratorioSessionBean extends BaseSessionBean implements LaboratorioSession {

	@Override
	public Laboratorio buscarPorId(Usuario usuario, Long id) throws ProblemaPermissaoException {

		Permission.LABORATORIO_BUSCAR.check(usuario);

		Laboratorio laboratorio = em.find(Laboratorio.class, id);

		PermissaoPorEstabelecimento.checkIfCanGetsEntity(usuario, laboratorio);

		return laboratorio;
	}

	@Override
	public List<Laboratorio> buscarTodos(Usuario usuario, Options params) throws ProblemaPermissaoException {

		Permission.LABORATORIO_LISTAR.check(usuario);

		Map<String, Object> queryParams = new HashMap<>();
		String queryString = "SELECT e FROM Laboratorio AS e";

		queryString += " WHERE e.estabelecimento = :estabelecimentoUsuario";
		queryParams.put("estabelecimentoUsuario", usuario.getEstabelecimento());

		if (params.getOrdenacao() != null) {
			queryString += "ORDER BY e." + params.getOrdenacao();
		}

		Query query = em.createQuery(queryString);

		Util.checkPagination(query, params);
		Util.setParams(query, queryParams);

		List<Laboratorio> laboratorios = (List<Laboratorio>) query.getResultList();
		detach(laboratorios);

		PermissaoPorEstabelecimento.checkIfCanGetsEntity(usuario, laboratorios);

		return laboratorios;
	}

	@Override
	public Laboratorio salvar(Usuario usuario, Laboratorio entidade) throws ProblemaPermissaoException {

		if (entidade.getId() == null) {
			Permission.LABORATORIO_CRIAR.check(usuario);

			entidade.setEstabelecimento(usuario.getEstabelecimento());
		} else {
			Permission.LABORATORIO_ALTERAR.check(usuario);
		}

		Laboratorio laboratorio = em.merge(entidade);

		PermissaoPorEstabelecimento.checkIfCanGetsEntity(usuario, laboratorio);

		return laboratorio;
	}

	@Override
	public void excluir(Usuario usuario, Long id) throws ProblemaPermissaoException {
		Permission.LABORATORIO_EXCLUIR.check(usuario);

		em.remove(buscarPorId(usuario, id));
	}

	@Override
	public List<Laboratorio> buscarPorFiltro(Usuario usuario, Options options, BaseFilter<Laboratorio> filtro)
			throws ProblemaPermissaoException {

		Permission.LABORATORIO_LISTAR.check(usuario);
		List<Laboratorio> laboratorios = new ArrayList<>();

		PermissaoPorEstabelecimento.checkIfCanGetsEntity(usuario, laboratorios);
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
