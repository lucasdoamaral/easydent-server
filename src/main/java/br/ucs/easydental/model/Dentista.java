package br.ucs.easydental.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import br.ucs.easydental.model.intf.Entidade;

@Entity
public class Dentista implements Entidade {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "dentista", sequenceName = "dentista_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dentista")
	private Long id;
	
	private String nome;

	private String CRO;
	
	private String estadoCRO;
	
	@OneToOne
	private Endereco endereco;
	
	@OneToMany
	private List<Especialidade> especialidades;
	
	@ManyToOne
	private Estabelecimento estabelecimento;
	
	public Dentista() {

	}
	
	@Override
	public Long getId() {
		return this.id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCRO() {
		return CRO;
	}

	public void setCRO(String cRO) {
		CRO = cRO;
	}

	public String getEstadoCRO() {
		return estadoCRO;
	}

	public void setEstadoCRO(String estadoCRO) {
		this.estadoCRO = estadoCRO;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidade) {
		this.especialidades = especialidade;
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
