package br.ucs.easydent.rest.entity;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.ucs.easydent.session.EntityEJB;
import br.ucs.easydent.sessionbean.QueryParams;
import br.ucs.easydental.model.intf.Entidade;

public abstract class EntityService<T extends Entidade> {

	protected abstract EntityEJB<T> getEJB();

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public T buscarPorId(@PathParam("id") Long id) {
		return getEJB().buscarPorId(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<T> listar(@QueryParam("offset") Integer primeiroRegistro, @QueryParam("max-results") Integer quantidadeRegistros,
			@QueryParam("sort") String ordenacao) {

		QueryParams parametros = new QueryParams(quantidadeRegistros, primeiroRegistro, ordenacao);
		return getEJB().buscarTodos(parametros);

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public T criar(T entidade) {
		return getEJB().salvar(entidade);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public T alterar(T entidade) {
		return getEJB().salvar(entidade);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void excluir(@PathParam("id") Long id) {
		getEJB().excluir(id);
	}

}
