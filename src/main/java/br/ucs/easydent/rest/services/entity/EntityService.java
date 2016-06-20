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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.ucs.easydent.app.RegistroNaoEncontradoException;
import br.ucs.easydent.app.exceptions.ProblemaPermissaoException;
import br.ucs.easydent.ejb.session.EntityEJB;
import br.ucs.easydent.ejb.session.UsuarioSession;
import br.ucs.easydent.ejb.sessionbean.Options;
import br.ucs.easydent.model.entity.Usuario;
import br.ucs.easydent.model.intf.Entidade;
import br.ucs.easydent.rest.services.Session;
import br.ucs.easydent.rest.services.Token;

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
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
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
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public T criar(T entidade, @HeaderParam("X-Auth-Token") String token) {
		return getEJB().salvar(getUserFromToken(token), entidade);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public T alterar(T entidade, @HeaderParam("X-Auth-Token") String token) {
		return getEJB().salvar(getUserFromToken(token), entidade);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void excluir(@PathParam("id") Long id, @HeaderParam("X-Auth-Token") String token) {
		getEJB().excluir(getUserFromToken(token), id);
	}

	@OPTIONS
	public void options() {
		System.out.println("Calling OPTIONS HTTP method.");
	}

	private Usuario getUserFromToken(String tokenValue) {
		Token token = Session.getToken(tokenValue);
		if (token != null && token.isValid()) {
			try {
				return usuarioSession.buscarPorLogin(token.getUsername());
			} catch (RegistroNaoEncontradoException e) {
				System.out.println("EntityService method called without an user.");
				return null;
			}
		} else {
			return null;
		}
	}

}
