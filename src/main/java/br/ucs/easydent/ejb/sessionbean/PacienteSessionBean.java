package br.ucs.easydent.ejb.sessionbean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.app.exceptions.ProblemaPermissaoException;
import br.ucs.easydent.app.exceptions.RegistroNaoEncontradoException;
import br.ucs.easydent.app.util.Util;
import br.ucs.easydent.ejb.session.PacienteSession;
import br.ucs.easydent.model.entity.Paciente;
import br.ucs.easydent.model.entity.Usuario;

@Stateless
public class PacienteSessionBean extends BaseSessionBean implements PacienteSession {

	public Paciente buscarPorId(Usuario usuario, Long id)
			throws ProblemaPermissaoException, RegistroNaoEncontradoException {

		Paciente paciente = em.find(Paciente.class, id);

		if (paciente == null) {
			throw new RegistroNaoEncontradoException();
		}

		// Check permissions
		switch (usuario.getTipoUsuarioEnum()) {
		case ADMIN:
			break;

		case DENTISTA:
		case GERENTE:
		case SECRETARIA:
			if (!usuario.getEstabelecimento().equals(paciente.getEstabelecimento())) {
				throw new ProblemaPermissaoException();
			}
			break;

		case PACIENTE:
			if (paciente.getUsuario() == null || !paciente.getUsuario().equals(usuario)) {
				throw new ProblemaPermissaoException();
			}
			break;

		default:
			throw new ProblemaPermissaoException();
		}

		return paciente;
	}

	public List<Paciente> buscarTodos(Usuario usuario, Options params) throws ProblemaPermissaoException {

		Map<String, Object> queryParams = new HashMap<>();

		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT e FROM Paciente AS e");
		queryString.append(" WHERE 1=1 ");

		// Check permissions
		switch (usuario.getTipoUsuarioEnum()) {

		case ADMIN:
			break;

		case DENTISTA:
		case GERENTE:
		case SECRETARIA:
			queryString.append(" AND e.estabelecimento = :estabelecimentoUsuario ");
			queryParams.put("estabelecimentoUsuario", usuario.getEstabelecimento());
			break;

		case PACIENTE:
		default:
			throw new ProblemaPermissaoException();
		}

		if (params.getOrdenacao() != null) {
			queryString.append(" ORDER BY e." + params.getOrdenacao());
		}

		Query query = em.createQuery(queryString.toString());
		Util.setParams(query, queryParams);
		Util.checkPagination(query, params);

		return query.getResultList();
	}

	public Paciente salvar(Usuario usuario, Paciente entidade) {

		// Se for paciente novo
		if (entidade.getId() == null) {

			if (entidade.getEstabelecimento() == null) {
				entidade.setEstabelecimento(usuario.getEstabelecimento());
			}

			// Deve buscar o próximo número para o estabelecimento
			Integer proximoCodigo = getProximoCodigoPacienteEstabelecimento(usuario.getEstabelecimento().getId());
			entidade.setCodigo(proximoCodigo);
		}

		return em.merge(entidade);
	}

	private Integer getProximoCodigoPacienteEstabelecimento(Long estabelecimentoId) {

		Query query = em
				.createQuery("SELECT MAX(e.codigo) FROM Paciente AS e WHERE e.estabelecimento.id = :estabelecimentoId");
		query.setParameter("estabelecimentoId", estabelecimentoId);

		Object resultado = query.getSingleResult();
		return resultado == null ? 1 : ((Integer) resultado) + 1;

	}

	public void excluir(Usuario usuario, Long id) {
		Paciente paciente = em.find(Paciente.class, id);
		if (paciente != null) {
			em.remove(paciente);
		}
	}

	public List<Paciente> buscarPorFiltro(Usuario usuario, Options options, BaseFilter<Paciente> filtro) {
		// TODO Criar método buscarPorFiltro em PacienteSessionBean
		throw new NotImplementedException("PacienteSessionBean/buscarPorFiltro");
	}

}
