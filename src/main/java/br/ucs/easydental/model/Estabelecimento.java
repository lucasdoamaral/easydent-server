package br.ucs.easydental.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import br.ucs.easydental.model.intf.Entidade;

@Entity
public class Estabelecimento implements Entidade {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "estabelecimento", sequenceName = "estabelecimento_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estabelecimento")
	private Long id;

	private String nome;
	private String cnpj;

	@OneToOne
	private Endereco endereco;

	public Long getId() {
		return this.id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
