package br.ucs.easydent.ejb.session;

import javax.ejb.Remote;

import br.ucs.easydent.app.exceptions.RegistroNaoEncontradoException;
import br.ucs.easydent.model.entity.Usuario;

@Remote
public interface UsuarioSession extends EntityEJB<Usuario> {

	Usuario buscarPorLogin(String login) throws RegistroNaoEncontradoException;

	Usuario buscarPorLoginOuEmail(String login) throws RegistroNaoEncontradoException;

}
