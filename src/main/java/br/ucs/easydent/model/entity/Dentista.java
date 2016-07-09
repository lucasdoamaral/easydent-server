package br.ucs.easydent.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import br.ucs.easydent.model.intf.EntidadeComEstabelecimento;

@Entity
public class Dentista implements EntidadeComEstabelecimento {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "dentista", sequenceName = "dentista_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dentista")
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column
	private String email;

	@Column
	private String celular;

	@Column(nullable = false)
	private Integer CRO;

	@Column(nullable = false)
	private String estadoCRO;

	@OneToOne
	private Endereco endereco;

	@OneToMany
	private List<Especialidade> especialidades;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Estabelecimento estabelecimento;

	@OneToMany
	private List<HorarioDisponivel> horarios;

	@OneToMany
	private List<HorarioEspecial> horariosEspeciais;

	public List<HorarioDisponivel> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<HorarioDisponivel> horarios) {
		this.horarios = horarios;
	}

	public List<HorarioEspecial> getHorariosEspeciais() {
		return horariosEspeciais;
	}

	public void setHorariosEspeciais(List<HorarioEspecial> horariosEspeciais) {
		this.horariosEspeciais = horariosEspeciais;
	}

	@OneToOne
	@JoinColumn(nullable = true)
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

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

	public Integer getCRO() {
		return CRO;
	}

	public void setCRO(Integer cRO) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dentista other = (Dentista) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
