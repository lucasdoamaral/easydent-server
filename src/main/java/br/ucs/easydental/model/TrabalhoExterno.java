package br.ucs.easydental.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import br.ucs.easydental.model.intf.Entidade;

@Entity
public class TrabalhoExterno implements Entidade {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "trabalhoExterno", sequenceName = "trabalho_externo_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trabalhoExterno")
	private Long id;

	@ManyToOne
	private Laboratorio laboratorioExterno;

	@ManyToOne
	private Paciente paciente;

	@ManyToOne
	private Dentista dentista;

	private String descricao;

	public TrabalhoExterno() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Laboratorio getLaboratorioExterno() {
		return laboratorioExterno;
	}

	public void setLaboratorioExterno(Laboratorio laboratorioExterno) {
		this.laboratorioExterno = laboratorioExterno;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Dentista getDentista() {
		return dentista;
	}

	public void setDentista(Dentista dentista) {
		this.dentista = dentista;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
