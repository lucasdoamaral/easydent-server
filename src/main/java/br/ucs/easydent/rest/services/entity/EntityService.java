package br.ucs.easydent.rest.services.entity;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import br.ucs.easydent.app.exceptions.HorarioNaoDisponivelException;
import br.ucs.easydent.app.exceptions.ProblemaPermissaoException;
import br.ucs.easydent.app.exceptions.RegistroNaoEncontradoException;
import br.ucs.easydent.ejb.session.EntityEJB;
import br.ucs.easydent.ejb.session.UsuarioSession;
import br.ucs.easydent.ejb.sessionbean.Options;
import br.ucs.easydent.model.entity.Usuario;
import br.ucs.easydent.model.intf.Entidade;
import br.ucs.easydent.rest.services.RestException;
import br.ucs.easydent.rest.services.auth.Session;
import br.ucs.easydent.rest.services.auth.Token;

public abstract class EntityService<T extends Entidade> {

	protected abstract EntityEJB<T> getEJB();

	@EJB
	private UsuarioSession usuarioSession;

	@Context
	private HttpHeaders cabecalho;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public T buscarPorId(@PathParam("id") Long id, @HeaderParam("X-Auth-Token") String token) {
		try {
			return getEJB().buscarPorId(getUserFromToken(token), id);
		} catch (ProblemaPermissaoException e) {
			throw new RestException(e);
		} catch (RegistroNaoEncontradoException e) {
			throw new RestException(e);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<T> listar(@QueryParam("offset") Integer primeiroRegistro,
			@QueryParam("max-results") Integer quantidadeRegistros, @QueryParam("sort") String ordenacao,
			@HeaderParam("X-Auth-Token") String token) {

		Options parametros = new Options(quantidadeRegistros, primeiroRegistro, ordenacao);

		try {
			return getEJB().buscarTodos(getUserFromToken(token), parametros);
		} catch (ProblemaPermissaoException e) {
			throw new RestException(e);
		}

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public T criar(T entidade, @HeaderParam("X-Auth-Token") String token) {
		try {
			return getEJB().salvar(getUserFromToken(token), entidade);
		} catch (ProblemaPermissaoException | HorarioNaoDisponivelException e) {
			throw new RestException(e);
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public T alterar(T entidade, @HeaderParam("X-Auth-Token") String token) {
		try {
			return getEJB().salvar(getUserFromToken(token), entidade);
		} catch (ProblemaPermissaoException | HorarioNaoDisponivelException e) {
			throw new RestException(e);
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void excluir(@PathParam("id") Long id, @HeaderParam("X-Auth-Token") String token) {
		try {
			getEJB().excluir(getUserFromToken(token), id);
		} catch (ProblemaPermissaoException e) {
			throw new RestException(e);
		}
	}

	@OPTIONS
	public void options() {
		// System.out.println("Calling OPTIONS HTTP method.");
	}

	protected Usuario getUserFromToken(String tokenValue) {
		Token token = Session.getToken(tokenValue);
		if (token != null && token.isValid()) {
			try {
				return usuarioSession.buscarPorLoginOuEmail(token.getUsername());
			} catch (RegistroNaoEncontradoException e) {
				System.out.println("EntityService method called without an user.");
				return null;
			}
		} else {
			return null;
		}
	}

}
