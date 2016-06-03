package br.ucs.easydental.enums;

import br.ucs.easydental.util.Util;

public enum SituacaoConsultaEnum implements EasyDentEnum {

	AGENDADA(0, "Agendada"), ATENDIDA(1, "Atendida"), CANCELADA(2, "Cancelada");

	private SituacaoConsultaEnum(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	private Integer id;
	private String nome;

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getNome() {
		return nome;
	}
	
	public static SituacaoConsultaEnum getById (Integer id) {
		return Util.getEnumById(SituacaoConsultaEnum.values(), id);
	}

}
