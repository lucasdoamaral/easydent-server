package br.ucs.easydent.app.exceptions;

import javax.ws.rs.core.Response.Status;

public class ExclusaoNaoPermitida extends EasydentException {

	private static final long serialVersionUID = 1L;

	private String mensagem;

	public ExclusaoNaoPermitida(String mensagem) {
		super(mensagem);
		this.mensagem = mensagem;
	}

	public ExclusaoNaoPermitida() {
		this("Não é permitida a exclusão desse registro!");
	}

	@Override
	public Status getStatus() {
		return Status.BAD_REQUEST;
	}

	@Override
	public String getMessage() {
		return mensagem;
	}

}
