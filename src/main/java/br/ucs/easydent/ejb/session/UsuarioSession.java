package br.ucs.easydent.ejb.session;

import javax.ejb.Remote;

import br.ucs.easydent.app.exceptions.ProblemaPermissaoException;
import br.ucs.easydent.app.exceptions.RegistroNaoEncontradoException;
import br.ucs.easydent.model.entity.Usuario;

@Remote
public interface UsuarioSession extends EntityEJB<Usuario> {

	Usuario buscarPorLogin(String login) throws RegistroNaoEncontradoException;

	Usuario buscarPorLoginOuEmail(String login) throws RegistroNaoEncontradoException;

	Usuario alterarSenha(Usuario usuario, String login, String senhaAntiga, String novaSenha)
			throws RegistroNaoEncontradoException, ProblemaPermissaoException;

	Usuario buscarPorLogin(String login, boolean removerSenha) throws RegistroNaoEncontradoException;

}
