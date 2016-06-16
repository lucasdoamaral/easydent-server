package br.ucs.easydent.rest.services.entity;

import javax.ejb.EJB;
import javax.ws.rs.Path;

import br.ucs.easydent.ejb.session.EntityEJB;
import br.ucs.easydent.ejb.session.UsuarioSession;
import br.ucs.easydent.model.entity.Usuario;

@Path("/usuarios")
public class UsuarioService extends EntityService<Usuario> {

	@EJB
	private UsuarioSession usuarioSession;

	@Override
	protected EntityEJB<Usuario> getEJB() {
		return usuarioSession;
	}

}
