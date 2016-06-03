package br.ucs.easydental.enums;

import br.ucs.easydental.util.Util;

public enum TipoLaboratorioEnum implements EasyDentEnum {

	RADIOLOGIA(1, "Laboratório de Radiologia"), PROTESE(2, "Laboratório Protético");

	private TipoLaboratorioEnum(Integer id, String nome) {
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
	
	public static TipoLaboratorioEnum getById (Integer id) {
		return Util.getEnumById(TipoLaboratorioEnum.values(), id);
	}

}
