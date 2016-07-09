package br.ucs.easydent.app.exceptions;

import javax.ws.rs.core.Response.Status;

public abstract class EasydentException extends Exception {

	private static final long serialVersionUID = 1L;

	abstract public Status getStatus();

	abstract public String getMessage();

	public EasydentException(String mensagem) {
		super(mensagem);
	}

}
