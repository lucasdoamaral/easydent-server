package br.ucs.easydent.model.enums;

import br.ucs.easydent.app.util.Util2;

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
		return Util2.getEnumById(SituacaoTrabalhoExternoEnum.values(), id);
	}

}
