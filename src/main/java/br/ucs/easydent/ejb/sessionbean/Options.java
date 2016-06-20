package br.ucs.easydent.ejb.sessionbean;

import java.io.Serializable;

public class Options implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer quantidadeRegistros;

	private Integer primeiroRegistro;

	private String ordenacao;

	public Options() {
		// TODO Criar construtor QueryParams
	}

	public Options(Integer quantidadeRegistros, Integer primeiroRegistro, String ordenacao) {
		this.quantidadeRegistros = quantidadeRegistros;
		this.primeiroRegistro = primeiroRegistro;
		this.ordenacao = ordenacao;
	}

	public Integer getQuantidadeRegistros() {
		return quantidadeRegistros;
	}

	public void setQuantidadeRegistros(Integer quantidadeRegistros) {
		this.quantidadeRegistros = quantidadeRegistros;
	}

	public Integer getPrimeiroRegistro() {
		return primeiroRegistro;
	}

	public void setPrimeiroRegistro(Integer primeiroRegistro) {
		this.primeiroRegistro = primeiroRegistro;
	}

	public String getOrdenacao() {
		return ordenacao;
	}

	public void setOrdenacao(String ordenacao) {
		this.ordenacao = ordenacao;
	}

}
