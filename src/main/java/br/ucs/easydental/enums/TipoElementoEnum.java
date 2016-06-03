package br.ucs.easydental.enums;

public enum TipoElementoEnum {

	DENTE11(1, "Dente 11"), 
	DENTE12(2, "Dente 12"), 
	DENTE13(3, "Dente 13"), 
	DENTE14(4, "Dente 14"), 
	DENTE15(5, "Dente 15"), 
	DENTE16(6, "Dente 16"), 
	DENTE17(7, "Dente 17"), 
	DENTE18(8, "Dente 18"), 
	DENTE21(9, "Dente 19"), 
	DENTE22(10, "Dente 22"), 
	DENTE23(11, "Dente 23"), 
	DENTE24(12, "Dente 24"), 
	DENTE25(13, "Dente 25"), 
	DENTE26(14, "Dente 26"), 
	DENTE27(15, "Dente 27"), 
	DENTE28(16, "Dente 28"), 
	DENTE31(17, "Dente 31"), 
	DENTE32(18, "Dente 32"), 
	DENTE33(19, "Dente 33"), 
	DENTE34(20, "Dente 34"), 
	DENTE35(21, "Dente 35"), 
	DENTE36(22, "Dente 36"), 
	DENTE37(23, "Dente 37"), 
	DENTE38(24, "Dente 38"), 
	DENTE41(25, "Dente 41"),
	DENTE42(26, "Dente 42"), 
	DENTE43(27, "Dente 43"), 
	DENTE44(28, "Dente 44"), 
	DENTE45(29, "Dente 45"), 
	DENTE46(30, "Dente 46"), 
	DENTE47(31, "Dente 47"), 
	DENTE48(32, "Dente 48"),

	LINGUA (33, "Língua"), 
	CEUDABOCA(34, "Céu da boca"), 
	GENGIVASUPERIOR (35, "Gengiva superior"), 
	GENGIVAINFERIOR (36, "Gengiva inferior"), 
	ARCADASUPERIOR (37, "Arcada superior"), 
	ARCADAINFERIOR (38, "Arcada inferior"),
	
	OUTROS(99, "Outros");

	private TipoElementoEnum(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	private Integer id;
	private String nome;

	public Integer getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public static TipoElementoEnum getById (Integer id) {
		for (TipoElementoEnum elemento : TipoElementoEnum.values()) {
			if (elemento.id == id) {
				return elemento;
			}
		}
		return TipoElementoEnum.OUTROS;
	}

}
