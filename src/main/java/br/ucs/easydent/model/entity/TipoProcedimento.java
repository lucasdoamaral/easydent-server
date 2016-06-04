package br.ucs.easydent.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.ucs.easydent.model.intf.Entidade;

@Entity
public class TipoProcedimento implements Entidade {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "tipoProcedimento", sequenceName = "tipo_procedimento_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoProcedimento")
	private Long id;
	
	@Column(nullable = false)
	private String nome;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
