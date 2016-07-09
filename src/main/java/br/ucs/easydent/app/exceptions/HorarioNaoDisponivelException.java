package br.ucs.easydent.app.exceptions;

import javax.ws.rs.core.Response.Status;

public class HorarioNaoDisponivelException extends EasydentException {

	private static final long serialVersionUID = 1L;
	private String message;

	public HorarioNaoDisponivelException(String message) {
		super(message);
		this.message = message;
	}

	public HorarioNaoDisponivelException() {
		this("O horário solicitado não está disponível.");
	}

	@Override
	public Status getStatus() {
		return Status.BAD_REQUEST;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
