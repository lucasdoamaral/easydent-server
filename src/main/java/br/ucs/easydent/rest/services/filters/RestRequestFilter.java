package br.ucs.easydent.rest.services.filters;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import br.ucs.easydent.ejb.session.LoginSession;
import br.ucs.easydent.rest.services.Session;
import br.ucs.easydent.rest.services.Token;

/**
 * Jersey HTTP Basic Auth filter
 * 
 * @author Deisss (LGPLv3)
 */
@ManagedBean
public class RestRequestFilter implements ContainerRequestFilter {


	@EJB
	private LoginSession loginSession;

	private static final String GET = "GET";
	private static final String AUTH_TOKEN = "X-AUTH-TOKEN";
	
	@Override
	public ContainerRequest filter(ContainerRequest request) throws WebApplicationException {

		if (skipAuthentication(request)) {
			return request;
		}

		Token token = getToken(request);
		if (token == null ||  !token.isValid()) {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}

		return request;
	}
	
	private String getAuthTokenFromRequest (ContainerRequest request) {
		String auth = request.getHeaderValue(AUTH_TOKEN);
		if (auth == null && !"".equals(auth)) {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
		return auth;
	}

	private boolean skipAuthentication(ContainerRequest containerRequest) {

		String method = containerRequest.getMethod();
		String path = containerRequest.getPath(true);

		return GET.equalsIgnoreCase(method) && (path.equals("application.wadl") || path.equals("application.wadl/xsd0.xsd"))
				|| path.endsWith("login");
	}

	private Token getToken(ContainerRequest request) {
		return Session.getToken(getAuthTokenFromRequest(request));
	}

}