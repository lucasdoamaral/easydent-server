package br.ucs.easydental.enums;

import br.ucs.easydental.util.Util;

public enum TipoUsuarioEnum implements EasyDentEnum {

	ADMIN(1, "Administrador"), DENTISTA(2, "Dentista"), SECRETARIA(3, "Secretário(a)"), GERENTE(4, "Gerente"), PACIENTE(5, "Paciente");
	
	private TipoUsuarioEnum (Integer id, String nome) {
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
	
	public static TipoUsuarioEnum getById (Integer id) {
		return Util.getEnumById(TipoUsuarioEnum.values(), id);
	}

}
