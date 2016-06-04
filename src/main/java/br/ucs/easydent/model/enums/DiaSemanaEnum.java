package br.ucs.easydent.model.enums;

public enum DiaSemanaEnum {

	SEGUNDA(1, "Segunda-feira"), TERCA(2, "Terça-Feira"), QUARTA(3, "Quarta-Feira"),
	QUINTA(5, "Quinta-feira"), SEXTA(6, "Sexta-Feira"), SABADO(7, "Sábado"), DOMINGO(8, "Domingo");
	
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
