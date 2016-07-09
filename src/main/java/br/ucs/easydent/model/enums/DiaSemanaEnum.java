package br.ucs.easydent.model.enums;

public enum DiaSemanaEnum {

	DOMINGO(1, "Domingo"), SEGUNDA(2, "Segunda-feira"), TERCA(3, "Terça-Feira"), QUARTA(4, "Quarta-Feira"),
	QUINTA(5, "Quinta-feira"), SEXTA(6, "Sexta-Feira"), SABADO(7, "Sábado");
	
	private DiaSemanaEnum (Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	private Integer id;
	private String nome;

	public String getNome() {
		return nome;
	}
	
	public Integer getId() {
		return id;
	}
	
	public static DiaSemanaEnum getById (Integer id) {
		for (DiaSemanaEnum diaSemana: DiaSemanaEnum.values()){
			if (diaSemana.getId().equals(id)){
				return diaSemana;
			}
		}
		return null;
	}
	
}
