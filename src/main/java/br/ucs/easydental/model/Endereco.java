package br.ucs.easydental.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.ucs.easydental.model.intf.Entidade;

@Entity
public class Endereco implements Entidade {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "endereco", sequenceName = "endereco_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco")
	private Long id;
	
	private String CEP;
	
	private String logradouro;
	
	private Integer numero;
	
	private String complemento;
	
	private String bairro;
	
	private String cidade;
	
	private String estado;

	public Long getId() {
		return id;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
