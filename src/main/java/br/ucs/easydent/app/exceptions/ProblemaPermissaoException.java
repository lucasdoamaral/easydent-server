package br.ucs.easydent.app.exceptions;

public class ProblemaPermissaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProblemaPermissaoException() {
		super("Você não possui permissão para acessar esse recurso/método.");
	}

}
