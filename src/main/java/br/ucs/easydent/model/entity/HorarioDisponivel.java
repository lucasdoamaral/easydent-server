package br.ucs.easydent.model.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.ucs.easydent.model.enums.DiaSemanaEnum;
import br.ucs.easydent.model.intf.Entidade;

@Entity
public class HorarioDisponivel implements Entidade {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "horario", sequenceName = "horario_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "horario")
	private Long id;

	@Column(nullable = false)
	private Integer fgDiaSemana;

	@Column(nullable = false)
	private Calendar horaInicial;

	@Column(nullable = false)
	private Calendar horaFinal;

	private Calendar horaAlmocoInicial;

	private Calendar horaAlmocoFinal;

	@Override
	public Long getId() {
		return id;
	}

	public DiaSemanaEnum getDiaSemanaEnum() {
		return DiaSemanaEnum.getById(fgDiaSemana);
	}

	public void setDiaSemanaEnum(DiaSemanaEnum diaSemana) {
		this.fgDiaSemana = diaSemana != null ? diaSemana.getId() : null;
	}

	public Integer getFgDiaSemana() {
		return fgDiaSemana;
	}

	public void setFgDiaSemana(Integer fgDiaSemana) {
		this.fgDiaSemana = fgDiaSemana;
	}

	public Calendar getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(Calendar horaInicial) {
		this.horaInicial = horaInicial;
	}

	public Calendar getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(Calendar horaFinal) {
		this.horaFinal = horaFinal;
	}

	public Calendar getHoraAlmocoInicial() {
		return horaAlmocoInicial;
	}

	public void setHoraAlmocoInicial(Calendar horaAlmocoInicial) {
		this.horaAlmocoInicial = horaAlmocoInicial;
	}

	public Calendar getHoraAlmocoFinal() {
		return horaAlmocoFinal;
	}

	public void setHoraAlmocoFinal(Calendar horaAlmocoFinal) {
		this.horaAlmocoFinal = horaAlmocoFinal;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
