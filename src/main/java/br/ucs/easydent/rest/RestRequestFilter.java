package br.ucs.easydent.rest;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import br.ucs.easydent.FalhaLoginException;
import br.ucs.easydent.session.LoginSession;
import br.ucs.easydental.model.Usuario;

/**
 * Jersey HTTP Basic Auth filter
 * 
 * @author Deisss (LGPLv3)
 */
@ManagedBean
public class RestRequestFilter implements ContainerRequestFilter {

	@EJB
	private LoginSession loginSession;

	@Context
	private HttpServletRequest request;

	private static final String GET = "GET";

	/**
	 * Apply the filter : check input request, validate or not with user auth
	 * 
	 * @param containerRequest
	 *            The request from Tomcat server
	 */
	@Override
	public ContainerRequest filter(ContainerRequest containerRequest) throws WebApplicationException {

		// GET, POST, PUT, DELETE, ...
		String method = containerRequest.getMethod();
		// myresource/get/56bCA for example
		String path = containerRequest.getPath(true);

		System.out.println("INTERCEPTANDO REQUISIÇÃO");

		// We do allow wadl to be retrieve
		if (GET.equalsIgnoreCase(method) && (path.equals("application.wadl") || path.equals("application.wadl/xsd0.xsd"))) {
			return containerRequest;
		}

		// Get the authentification passed in HTTP headers parameters
		String auth = containerRequest.getHeaderValue("authorization");

		// If the user does not have the right (does not provide any HTTP Basic Auth)
		if (auth == null) {
			System.out.println("Nao foi passado nenhum parametro AUTH");
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}

		// lap : loginAndPassword
		String[] lap = BasicAuth.decode(auth);
		System.out.println("Usuario e senha: " + lap[0] + " " + lap[1]);

		// If login or password fail
		if (lap == null || lap.length != 2) {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}

		// DO YOUR DATABASE CHECK HERE (replace that line behind)...
		// TODO Utilizar usuário do JBoss nesse ponto?
		System.out.println("loginSession == null ? " + (loginSession == null));
		Usuario usuario = null;
		try {
			usuario = loginSession.login(lap[0], lap[1]);
		} catch (FalhaLoginException e) {
			System.err.println(e.getMessage());
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}

		request.setAttribute("usuario_logado", usuario);

		return containerRequest;
	}
}