package br.ucs.easydental.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class HorarioEspecial extends HorarioDisponivel {

	private static final long serialVersionUID = 1L;

	@Column
	private Calendar data;

	@Column
	private Boolean disponivel;

	public HorarioEspecial() {
		// TODO Criar construtor HorarioEspecial
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

}
