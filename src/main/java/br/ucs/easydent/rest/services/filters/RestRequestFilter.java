package br.ucs.easydent.rest.services.filters;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import br.ucs.easydent.app.RegistroNaoEncontradoException;
import br.ucs.easydent.ejb.session.LoginSession;
import br.ucs.easydent.ejb.session.UsuarioSession;
import br.ucs.easydent.model.entity.Usuario;
import br.ucs.easydent.rest.services.Session;
import br.ucs.easydent.rest.services.Token;

/**
 * Jersey HTTP Basic Auth filter
 * 
 * @author Deisss (LGPLv3)
 */
@ManagedBean
public class RestRequestFilter implements ContainerRequestFilter {

	private static final boolean AUTENTICACAO_NECESSARIA = false;
	private static final boolean PULAR_VALIDACAO = true;

	@EJB
	private LoginSession loginSession;

	@EJB
	private UsuarioSession usuarioSession;

	@Context
	private HttpServletRequest httpRequest;

	private static final String GET_HTTP_METHOD = "GET";
	private static final String OPTIONS_HTTP_METHOD = "OPTIONS";
	private static final String AUTH_TOKEN = "X-AUTH-TOKEN";

	@Override
	public ContainerRequest filter(ContainerRequest request) throws WebApplicationException {

		if (skipAuthentication(request)) {
			return request;
		}

		Token token = getToken(request);
		if (token == null || !token.isValid()) {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}

		Usuario usuario = null;
		try {
			usuario = usuarioSession.buscarPorLogin(token.getUsername());
		} catch (RegistroNaoEncontradoException e) {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
		httpRequest.setAttribute("usuario_logado", usuario);

		return request;
	}

	private String getAuthTokenFromRequest(ContainerRequest request) {
		String auth = request.getHeaderValue(AUTH_TOKEN);
		if (auth == null && !"".equals(auth)) {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
		return auth;
	}

	private boolean skipAuthentication(ContainerRequest containerRequest) {

		String method = containerRequest.getMethod();
		String path = containerRequest.getPath(true);

		// Se for solicitado o esquema do webservice
		if (GET_HTTP_METHOD.equalsIgnoreCase(method) && (path.equals("application.wadl"))
				|| path.equals("application.wadl/xsd0.xsd")) {
			return PULAR_VALIDACAO;
		}

		// Está solicitando login
		if (path.endsWith("login")) {
			return PULAR_VALIDACAO;
		}

		// Método OPTIONS
		if (OPTIONS_HTTP_METHOD.equalsIgnoreCase(method)) {
			return PULAR_VALIDACAO;
		}

		// Criando usuário
		if (path.endsWith("usuarios") && "POST".equalsIgnoreCase(method)) {
			return PULAR_VALIDACAO;
		}

		return AUTENTICACAO_NECESSARIA;

	}

	private Token getToken(ContainerRequest request) {
		return Session.getToken(getAuthTokenFromRequest(request));
	}

}