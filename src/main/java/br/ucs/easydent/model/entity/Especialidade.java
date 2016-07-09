package br.ucs.easydent.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import br.ucs.easydent.model.intf.EntidadeComEstabelecimento;

@Entity
public class Especialidade implements EntidadeComEstabelecimento {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "especialidade", sequenceName = "especialidade_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "especialidade")
	private Long id;
	
	@Column(nullable = false)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Estabelecimento estabelecimento;

	@Override
	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

}
