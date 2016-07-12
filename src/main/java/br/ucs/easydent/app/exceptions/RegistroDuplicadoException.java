package br.ucs.easydent.app.exceptions;

import javax.ws.rs.core.Response.Status;

public class RegistroDuplicadoException extends EasydentException {

	private static final long serialVersionUID = 1L;

	private String message;

	@Override
	public Status getStatus() {
		return Status.CONFLICT;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public RegistroDuplicadoException(String message) {
		super(message);
		this.message = message;
	}

}
