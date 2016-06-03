package br.ucs.easydental.enums;

import br.ucs.easydental.util.Util;

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
		return Util.getEnumById(TipoInformacaoContatoEnum.values(), id);
	}
	
}
