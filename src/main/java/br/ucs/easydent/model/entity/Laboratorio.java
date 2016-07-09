package br.ucs.easydent.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import br.ucs.easydent.model.intf.EntidadeComEstabelecimento;

@Entity
public class Laboratorio implements EntidadeComEstabelecimento {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "laboratorio", sequenceName = "laboratorio_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "laboratorio")
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column
	private String email;
	
	@Column
	private String telefone;

	@Column
	private String celular;

	@Column(unique = true)
	private String cnpj;

	@Column(nullable = false)
	private Integer fgTipoLaboratorio;

	@OneToOne
	private Endereco endereco;

	@ManyToOne
	private Estabelecimento estabelecimento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Integer getFgTipoLaboratorio() {
		return fgTipoLaboratorio;
	}

	public void setFgTipoLaboratorio(Integer fgTipoLaboratorio) {
		this.fgTipoLaboratorio = fgTipoLaboratorio;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
