package br.ucs.easydent.ejb.sessionbean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.app.exceptions.EasydentException;
import br.ucs.easydent.app.exceptions.ProblemaPermissaoException;
import br.ucs.easydent.app.exceptions.RegistroDuplicadoException;
import br.ucs.easydent.app.exceptions.RegistroNaoEncontradoException;
import br.ucs.easydent.ejb.session.EstabelecimentoSession;
import br.ucs.easydent.ejb.session.UsuarioSession;
import br.ucs.easydent.model.entity.Estabelecimento;
import br.ucs.easydent.model.entity.Usuario;
import br.ucs.easydent.model.enums.TipoUsuarioEnum;

@Stateless
public class UsuarioSessionBean extends BaseSessionBean implements UsuarioSession {

	private static final boolean MANTER_SENHA = false;
	private static final boolean REMOVER_SENHA = true;
	@EJB
	private EstabelecimentoSession estabelecimentoSessionBean;

	public Usuario buscarPorId(Usuario usuario, Long id) {
		return this.buscarPorId(usuario, id, REMOVER_SENHA);
	}

	public Usuario buscarPorId(Usuario usuario, Long id, boolean removerSenha) {
		Usuario _usuario = em.find(Usuario.class, id);
		detachUsuario(_usuario);
		if (removerSenha) {
			removePassword(_usuario);
		}
		return _usuario;
	}

	private void removePassword(Usuario usuario) {
		em.detach(usuario);
		usuario.setSenha(null);
	}

	public List<Usuario> buscarTodos(Usuario usuario, Options params) throws ProblemaPermissaoException {

		throw new ProblemaPermissaoException("Você não tem permisão para visualizar a lista de usuários cadastrados");

		/*
		 * String queryString = "SELECT e FROM Usuario AS e"; if
		 * (params.getOrdenacao() != null) { queryString += " ORDER BY e." +
		 * params.getOrdenacao(); }
		 * 
		 * Query query = em.createQuery(queryString);
		 * Util.checkPagination(query, params);
		 * 
		 * List<Usuario> usuarios = (List<Usuario>) query.getResultList();
		 * detachUsuarios(usuarios);
		 * 
		 * return usuarios;
		 * 
		 */
	}

	public Usuario salvar(Usuario usuario, Usuario entidade) throws EasydentException {

		if (TipoUsuarioEnum.ADMIN.equals(entidade.getTipoUsuarioEnum())) {
			throw new ProblemaPermissaoException("Você não tem permissão para criar usuários administradores!");
		}

		// Se for usuário novo
		if (entidade.getId() == null) {

			// Verifica a disponibilidade do nome de usuário e do e-mail
			verificarDisponibilidadeLoginEmail(entidade.getLogin(), entidade.getEmail());

			// Se for um estabelecimento novo
			if (entidade.getEstabelecimento().getId() == null) {
				Estabelecimento novoEstb = null;
				novoEstb = estabelecimentoSessionBean.salvar(usuario, entidade.getEstabelecimento());
				// em.flush();
				entidade.setEstabelecimento(novoEstb);
			}
			if (entidade.getTipoUsuarioEnum() == null) {
				entidade.setTipoUsuarioEnum(TipoUsuarioEnum.GERENTE);
			}

		} else {

			// Se não for um usuário novo, busca o antigo e atualiza os campos
			Usuario usuarioJaCadastrado = null;

			String loginOuEmail = entidade.getLogin() != null ? entidade.getLogin() : entidade.getEmail();
			usuarioJaCadastrado = buscarPorLoginOuEmail(loginOuEmail, MANTER_SENHA);

			entidade.setSenha(usuarioJaCadastrado.getSenha());
			em.flush();

			estabelecimentoSessionBean.salvar(usuario, entidade.getEstabelecimento());
		}

		Usuario _usuario = em.merge(entidade);
		// FIXME testando se é o remove password que está causando problema
		// removePassword(_usuario);
		return _usuario;
	}

	private void verificarDisponibilidadeLoginEmail(String login, String email) throws RegistroDuplicadoException {

		if (login != null) {
			try {
				this.buscarPorLogin(login);
				throw new RegistroDuplicadoException("Esse login já está em uso");
			} catch (RegistroNaoEncontradoException e) {
				// Se não encontrar, ok!
			}
		}

		if (email != null) {
			try {
				this.buscarPorLoginOuEmail(email);
				throw new RegistroDuplicadoException("Esse e-mail já foi informado anteriormente");
			} catch (RegistroNaoEncontradoException e) {
				// Se não encontrar, ok!
			}
		}
	}

	public void excluir(Usuario usuario, Long id) {
		Usuario _usuario = em.find(Usuario.class, id);
		if (_usuario != null) {
			em.remove(_usuario);
		}
	}

	public List<Usuario> buscarPorFiltro(Usuario usuario, Options options, BaseFilter<Usuario> filtro) {
		// TODO Criar método buscarPorFiltro em UsuarioSessionBean
		throw new NotImplementedException("UsuarioSessionBean/buscarPorFiltro");
	}

	@SuppressWarnings("unused")
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
		return buscarPorLoginOuEmail(login, REMOVER_SENHA);
	}

	@Override
	public Usuario buscarPorLogin(String login, boolean removerSenha) throws RegistroNaoEncontradoException {

		Query query = em.createQuery("SELECT e FROM Usuario AS e WHERE e.login = :login");
		query.setParameter("login", login);

		try {
			Usuario _usuario = (Usuario) query.getSingleResult();
			if (removerSenha) {
				removePassword(_usuario);
			}
			return _usuario;
		} catch (NoResultException e) {
			throw new RegistroNaoEncontradoException();
		}
	}

	@Override
	public Usuario buscarPorLoginOuEmail(String loginOuEmail) throws RegistroNaoEncontradoException {
		return this.buscarPorLoginOuEmail(loginOuEmail, REMOVER_SENHA);
	}

	public Usuario buscarPorLoginOuEmail(String loginOuEmail, boolean removerSenha)
			throws RegistroNaoEncontradoException {

		Query query = em
				.createQuery("SELECT e FROM Usuario AS e WHERE e.login = :loginOuEmail OR e.email = :loginOuEmail ");
		query.setParameter("loginOuEmail", loginOuEmail);

		try {
			Usuario usuario = (Usuario) query.getSingleResult();
			if (removerSenha) {
				removePassword(usuario);
			}
			return usuario;
		} catch (NoResultException e) {
			throw new RegistroNaoEncontradoException();
		}
	}

	@Override
	public Usuario alterarSenha(Usuario usuario, String login, String senhaAntiga, String novaSenha)
			throws RegistroNaoEncontradoException, ProblemaPermissaoException {

		Usuario _usuario = this.buscarPorLogin(login, MANTER_SENHA);

		if (!usuario.equals(_usuario)) {
			throw new ProblemaPermissaoException("Somente o próprio usuário pode alterar a sua senha!");
		}

		if (!_usuario.getSenha().equals(senhaAntiga)) {
			throw new ProblemaPermissaoException("A senha atual está incorreta!");
		}

		_usuario.setSenha(novaSenha);
		return _usuario;
	}

}
