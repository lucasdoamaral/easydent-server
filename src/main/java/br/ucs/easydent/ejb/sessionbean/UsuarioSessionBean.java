package br.ucs.easydent.ejb.sessionbean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;

import br.ucs.easydent.app.RegistroNaoEncontradoException;
import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.app.util.Util;
import br.ucs.easydent.ejb.session.EstabelecimentoSession;
import br.ucs.easydent.ejb.session.UsuarioSession;
import br.ucs.easydent.model.entity.Estabelecimento;
import br.ucs.easydent.model.entity.Usuario;

@Stateless
public class UsuarioSessionBean extends BaseSessionBean implements UsuarioSession {

	@EJB
	private EstabelecimentoSession estabelecimentoSessionBean;

	public Usuario buscarPorId(Long id) {
		Usuario usuario = em.find(Usuario.class, id);
		detachUsuario(usuario);
		return usuario;
	}

	public List<Usuario> buscarTodos(QueryParams params) {

		String queryString = "SELECT e FROM Usuario AS e";
		if (params.getOrdenacao() != null) {
			queryString += " ORDER BY e." + params.getOrdenacao();
		}

		Query query = em.createQuery(queryString);
		Util.checkPagination(query, params);

		List<Usuario> usuarios = (List<Usuario>) query.getResultList();
		detachUsuarios(usuarios);

		return usuarios;
	}

	public Usuario salvar(Usuario entidade) {

		// Se for usuário novo
		if (entidade.getId() == null) {

			// Se for um estabelecimento novo
			if (entidade.getEstabelecimento().getId() == null) {
				Estabelecimento estabelecimentoSalvo = estabelecimentoSessionBean.salvar(entidade.getEstabelecimento());
				entidade.setEstabelecimento(estabelecimentoSalvo);
			}
		}

		return em.merge(entidade);
	}

	public void excluir(Long id) {
		Usuario usuario = em.find(Usuario.class, id);
		if (usuario != null) {
			em.remove(usuario);
		}
	}

	public List<Usuario> buscarPorFiltro(BaseFilter<Usuario> filtro) {
		// TODO Criar método buscarPorFiltro em UsuarioSessionBean
		throw new NotImplementedException("UsuarioSessionBean/buscarPorFiltro");
	}

	private void detachUsuarios(List<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			detachUsuario(usuario);
		}
	}

	private void detachUsuario(Usuario usuario) {
		// nada para carregar
	}

	@Override
	public Usuario buscarPorLogin(String login) throws RegistroNaoEncontradoException {

		Query query = em.createQuery("SELECT e FROM Usuario AS e WHERE e.login = :login");
		query.setParameter("login", login);

		try {
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			throw new RegistroNaoEncontradoException();
		}
	}

}
