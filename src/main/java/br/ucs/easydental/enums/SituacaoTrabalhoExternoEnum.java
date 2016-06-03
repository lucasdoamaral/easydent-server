package br.ucs.easydental.enums;

import br.ucs.easydental.util.Util;

public enum SituacaoTrabalhoExternoEnum implements EasyDentEnum {

	PENDENTE(0, "Pendente"), CONCLUIDO(1, "Concluído"), CANCELADO(2, "Cancelado");

	private SituacaoTrabalhoExternoEnum(Integer id, String nome) {
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
	
	public static SituacaoTrabalhoExternoEnum getById (Integer id) {
		return Util.getEnumById(SituacaoTrabalhoExternoEnum.values(), id);
	}

}
