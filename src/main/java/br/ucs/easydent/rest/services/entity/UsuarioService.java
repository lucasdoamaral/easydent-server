package br.ucs.easydent.rest.services.entity;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.ucs.easydent.app.dto.filtro.AlteracaoSenhaDto;
import br.ucs.easydent.app.exceptions.ProblemaPermissaoException;
import br.ucs.easydent.app.exceptions.RegistroNaoEncontradoException;
import br.ucs.easydent.ejb.session.EntityEJB;
import br.ucs.easydent.ejb.session.UsuarioSession;
import br.ucs.easydent.model.entity.Usuario;
import br.ucs.easydent.rest.services.RestException;

@Path("/usuarios")
public class UsuarioService extends EntityService<Usuario> {

	// private static final boolean MANTER_SENHA = false;
	@EJB
	private UsuarioSession usuarioSession;

	@Override
	protected EntityEJB<Usuario> getEJB() {
		return usuarioSession;
	}

	@GET
	@Path("/logado")
	public Usuario buscarUsuarioLogado(@HeaderParam("X-Auth-Token") String token) throws RestException {
		Usuario usuario = getUserFromToken(token);
		try {
			return usuarioSession.buscarPorLoginOuEmail(usuario.getLogin());
		} catch (RegistroNaoEncontradoException e) {
			throw new RestException(e);
		}
	}

	@POST
	@Path("/alterarsenha")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario alterarSenha(@HeaderParam("X-Auth-Token") String token, AlteracaoSenhaDto alteracaoSenha)
			throws RestException {
		try {
			return usuarioSession.alterarSenha(getUserFromToken(token), alteracaoSenha.getLogin(),
					alteracaoSenha.getSenhaAtual(), alteracaoSenha.getNovaSenha());
		} catch (RegistroNaoEncontradoException | ProblemaPermissaoException e) {
			throw new RestException(e);
		}
	}

	@GET
	@Path("/checkusername/{username}")
	public Boolean verificarNomeUsuarioDisponivel(@PathParam("username") String login) {
		try {
			usuarioSession.buscarPorLogin(login);
		} catch (RegistroNaoEncontradoException e) {
			return true;
		}
		return false;
	}

	@GET
	@Path("/checkemail/{email}")
	public Boolean verificarEmailDisponivel(@PathParam("email") String email) {
		try {
			usuarioSession.buscarPorLoginOuEmail(email);
		} catch (RegistroNaoEncontradoException e) {
			return true;
		}
		return false;
	}

	@OPTIONS
	public void options() {
		// System.out.println("Calling OPTIONS HTTP method.");
	}

}
