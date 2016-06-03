package br.ucs.easydent.session;

import javax.ejb.Remote;

import br.ucs.easydental.model.Usuario;

@Remote
public interface UsuarioSession  extends EntityEJB<Usuario> {

}
