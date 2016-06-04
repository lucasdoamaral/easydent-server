package br.ucs.easydent.rest.services.filters;

import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import br.ucs.easydent.app.exceptions.FalhaLoginException;
import br.ucs.easydent.ejb.session.LoginSession;
import br.ucs.easydent.model.entity.Usuario;
import br.ucs.easydent.rest.services.BasicAuth;

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

	private static Logger logger = Logger.getLogger("br.ucs.easydent.rest.RestRequestFilter");

	/**
	 * Apply the filter : check input request, validate or not with user auth
	 * 
	 * @param containerRequest
	 *            The request from Tomcat server
	 */
	@Override
	public ContainerRequest filter(ContainerRequest containerRequest) throws WebApplicationException {

		String method = containerRequest.getMethod();
		String path = containerRequest.getPath(true);

		if (GET.equalsIgnoreCase(method)
				&& (path.equals("application.wadl") || path.equals("application.wadl/xsd0.xsd"))) {
			return containerRequest;
		}

		String auth = containerRequest.getHeaderValue("authorization");
		if (auth == null && !"".equals(auth)) {
			logger.warning("Requisicao realizada sem cabecalho de autorizacao.");
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}

		String[] lap = BasicAuth.decode(auth);
		if (lap == null || lap.length != 2) {
			logger.warning("Informacoes invalidas no cabecalho de autorizacao.");
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}

		try {
			Usuario usuario = loginSession.login(lap[0], lap[1]);
			request.setAttribute("usuario_logado", usuario);
		} catch (FalhaLoginException e) {
			logger.warning(e.getMessage());
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}

		return containerRequest;
	}
}