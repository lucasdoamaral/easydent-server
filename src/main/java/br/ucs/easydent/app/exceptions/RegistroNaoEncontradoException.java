package br.ucs.easydent.app.exceptions;

import javax.ws.rs.core.Response.Status;

public class RegistroNaoEncontradoException extends EasydentException {

	private static final long serialVersionUID = 1L;
	private String mensagem;

	public RegistroNaoEncontradoException() {
		super("Registro não encontrado.");
	}

	public RegistroNaoEncontradoException(String mensagem) {
		super(mensagem);
		this.mensagem = mensagem;
	}

	public Status getStatus() {
		return Status.NO_CONTENT;
	}

	@Override
	public String getMessage() {
		return mensagem;
	}

}
