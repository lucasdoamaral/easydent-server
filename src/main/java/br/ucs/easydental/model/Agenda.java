package br.ucs.easydental.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.ucs.easydental.model.intf.Entidade;

@Entity
public class Agenda implements Entidade {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "agenda", sequenceName = "agenda_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agenda")
	private Long id;

	@Column
	private String descricao;
	
	@OneToMany
	private List<HorarioDisponivel> horarios;
	
	@OneToMany
	private List<HorarioEspecial> horariosEspeciais;

	@ManyToOne
	private Dentista dentista;

	public Agenda() {
		// TODO Auto-generated constructor stub
	}

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

	public Dentista getDentista() {
		return dentista;
	}

	public void setDentista(Dentista dentista) {
		this.dentista = dentista;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

}
