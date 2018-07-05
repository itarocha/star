package br.itarocha.star.model;

public enum EnumSigno {
	//SOL(0, "sol", "A", "Sol"),
	
	AR(0, "ar", "a", "Áries"),
	TO(1, "to", "b", "Touro"),
	GE(2, "ge", "c", "Gêmeos"),
	CA(3, "ca", "d", "Câncer"),
	LE(4, "le", "e", "Leão"),
	VI(5, "vi", "f", "Virgem"),
	LI(6, "li", "g", "Libra"),
	ES(7, "es", "h", "Escorpião"),
	SG(8, "sg", "i", "Sagitário"),
	CP(9, "cp", "j", "Capricórnio"),
	AQ(10, "aq", "k", "Aquário"),
	PE(11, "pe", "l", "Peixes");
	
	private Integer codigo;
	private String sigla;
	private String letra;
	private String nome;
	
	EnumSigno(Integer codigo, String sigla, String letra, String nome){
		this.codigo = codigo;
		this.sigla = sigla;
		this.letra = letra;
		this.nome = nome;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getSigla() {
		return sigla;
	}

	public String getNome() {
		return nome;
	}

	public String getLetra() {
		return letra;
	}
	
	public static EnumSigno getByCodigo(Integer codigo) {
		for (EnumSigno x : EnumSigno.values()) {
			if (x.getCodigo().equals(codigo)) {
				return x;
			}
		}
		return EnumSigno.AR;
	}
			
	public static EnumSigno getBySigla(String sigla) {
		for (EnumSigno x : EnumSigno.values()) {
			if (x.getSigla().equalsIgnoreCase(sigla.toLowerCase())) {
				return x;
			}
		}
		return EnumSigno.AR;
	}
			
}
