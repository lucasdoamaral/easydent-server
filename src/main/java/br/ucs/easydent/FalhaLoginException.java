package br.ucs.easydent;

public class FalhaLoginException extends Exception {

	private static final long serialVersionUID = 1L;

	public FalhaLoginException() {
		super();
	}

	public FalhaLoginException(String mensagem) {
		super(mensagem);
	}

	public FalhaLoginException(String usuario, String mensagem) {
		super("Falha de login para o usuário [" + usuario + "]. \n" + mensagem);
	}

}
