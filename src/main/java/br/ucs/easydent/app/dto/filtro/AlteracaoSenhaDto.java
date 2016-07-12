package br.ucs.easydent.app.dto.filtro;

import java.io.Serializable;

public class AlteracaoSenhaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;

	private String senhaAtual;

	private String novaSenha;

	public AlteracaoSenhaDto() {
		// TODO Auto-generated constructor stub
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

}
