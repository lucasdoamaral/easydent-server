package br.ucs.easydent.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import br.ucs.easydent.model.intf.Entidade;

@Entity
public class Laboratorio implements Entidade {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "laboratorio", sequenceName = "laboratorio_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "laboratorio")
	private Long id;

	@Column(nullable = false)
	private String nome;

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

}
