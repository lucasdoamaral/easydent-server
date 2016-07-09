package br.ucs.easydent.app.exceptions;

import javax.ws.rs.core.Response.Status;

public class FalhaLoginException extends EasydentException {

	private static final long serialVersionUID = 1L;
	private String mensagem;

	public FalhaLoginException() {
		super("Falha de login");
	}

	public FalhaLoginException(String mensagem) {
		super(mensagem);
		this.mensagem = mensagem;
	}

	public FalhaLoginException(String usuario, String mensagem) {
		super("Falha de login para o usuário [" + usuario + "]. \n" + mensagem);
		this.mensagem = mensagem;
	}

	public String getMessage() {
		return mensagem;
	}

	public Status getStatus() {
		return Status.UNAUTHORIZED;
	}

}
