package br.ucs.easydent.rest.services.auth;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.ucs.easydent.AuthenticatedUser;
import br.ucs.easydent.app.exceptions.FalhaLoginException;
import br.ucs.easydent.ejb.session.LoginSession;
import br.ucs.easydent.model.entity.Usuario;

@Path("/login")
public class LoginService {

	private static final String HEADER_AUTHORIZATION = "authorization";

	@EJB
	private LoginSession loginSession;

	private Logger logger = Logger.getLogger("LoginService");

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response token(@Context HttpServletRequest request) {

		LoginInfo info = getLoginFromRequest(request);

		Usuario usuario = null;
		try {
			logger.info("Usuário: " + info.nomeUsuario);
			usuario = loginSession.login(info.nomeUsuario, info.senha);
		} catch (FalhaLoginException e) {
			logger.warning(e.getMessage());
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}

		Token token = new Token(info.nomeUsuario);
		
		storeToken(token);

		AuthenticatedUser userResponse = new AuthenticatedUser();
		
		userResponse.setLoginUsuario(usuario.getLogin());
		userResponse.setNomeUsuario(usuario.getNome());
		userResponse.setPerfilUsuario(usuario.getTipoUsuarioEnum().toString());

		userResponse.setToken(token.getValue());
		userResponse.setRedirectURI(info.redirectURI);

		return Response.ok(userResponse).build();

	}

	private LoginInfo getLoginFromRequest(HttpServletRequest request) {
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

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.nomeUsuario = lap[0];
		loginInfo.senha = lap[1];

		//TODO get redirect uri
		loginInfo.redirectURI = "";
		
		return loginInfo;

	}

	private void storeToken(Token token) {
		Session.storeToken(token);
	}

	private class LoginInfo {
		String nomeUsuario;
		String senha;
		String redirectURI;
	}

}
