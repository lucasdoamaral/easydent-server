package br.ucs.easydent.model.enums;

import br.ucs.easydent.app.util.Util2;

public enum TipoInformacaoContatoEnum implements EasyDentEnum {

	CELULAR(0, "Celular"), TELEFONE (1, "Telefone"), EMAIL(2, "E-mail");
	
	private TipoInformacaoContatoEnum (int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	private int id;
	private String nome;
	
	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public String getNome() {
		return nome;
	}
	
	public static TipoInformacaoContatoEnum getById (Integer id) {
		return Util2.getEnumById(TipoInformacaoContatoEnum.values(), id);
	}
	
}
