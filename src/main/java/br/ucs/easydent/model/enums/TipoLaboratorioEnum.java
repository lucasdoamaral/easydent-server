package br.ucs.easydent.model.enums;

import br.ucs.easydent.app.util.Util2;

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
		return Util2.getEnumById(TipoLaboratorioEnum.values(), id);
	}

}
