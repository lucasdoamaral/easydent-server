package br.ucs.easydent.model.enums;

import br.ucs.easydent.app.util.Util2;

public enum SituacaoConsultaEnum implements EasyDentEnum {

	AGENDADA(0, "Agendada"), ATENDIDA(1, "Atendida"), CANCELADA(2, "Cancelada"), NAO_COMPARECIDA(3, "Falta");

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

	public static SituacaoConsultaEnum getById(Integer id) {
		return Util2.getEnumById(SituacaoConsultaEnum.values(), id);
	}

}
