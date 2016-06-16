package br.ucs.easydent.app;

public class RegistroNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public RegistroNaoEncontradoException() {
		super("Registro não encontrado.");
	}

	public RegistroNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public RegistroNaoEncontradoException(Throwable t) {
		super(t);
	}

}
