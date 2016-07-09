package br.ucs.easydent.rest.services;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.ucs.easydent.app.exceptions.EasydentException;
import br.ucs.easydent.security.ErrorMessage;

public class RestException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	public RestException(EasydentException e) {
		this(e.getStatus(), e.getMessage());
	}

	private RestException(Status status, String message) {
		super(Response.status(status).entity(new ErrorMessage(status, message)).type(MediaType.APPLICATION_JSON)
				.build());
	}

}
