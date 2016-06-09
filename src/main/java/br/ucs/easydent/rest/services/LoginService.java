package br.ucs.easydent.rest.services;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.ucs.easydent.app.exceptions.FalhaLoginException;
import br.ucs.easydent.ejb.session.LoginSession;

@Path("/login")
public class LoginService {

	private static final String HEADER_AUTHORIZATION = "authorization";

	@EJB
	private LoginSession loginSession;

	private Logger logger = Logger.getLogger("LoginService");

	@GET
	public Response token(@Context HttpServletRequest request) {

		UsuarioSenha info = getLoginFromRequest(request);

		try {
			logger.info("Usuário: " + info.usuario);
			loginSession.login(info.usuario, info.senha);
		} catch (FalhaLoginException e) {
			logger.warning(e.getMessage());
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}

		Token token = new Token(info.usuario);
		storeToken(token);

		return Response.ok(token.getValue()).build();

	}

	private UsuarioSenha getLoginFromRequest(HttpServletRequest request) {
		String auth = request.getHeader(HEADER_AUTHORIZATION);
		
		if (auth == null && !"".equals(auth)) {
			logger.warning("Requisicao realizada sem cabecalho de autorizacao.");
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}

		String[] lap = BasicAuthUtil.decode(auth);
		if (lap == null || lap.length != 2) {
			logger.warning("Informacoes invalidas no cabecalho de autorizacao.");
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}

		return new UsuarioSenha(lap[0], lap[1]);
	}

	private void storeToken(Token token) {
		Session.storeToken(token);
	}

	private class UsuarioSenha {
		String usuario;
		String senha;

		public UsuarioSenha(String usuario, String senha) {
			this.usuario = usuario;
			this.senha = senha;
		}
	}

}
