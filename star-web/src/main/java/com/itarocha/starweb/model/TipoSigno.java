package com.itarocha.starweb.model;

public enum TipoSigno {

    XX("*** Descrição ***"),
    AR("Áries"),
    TO("Touro"),
    GE("Gêmeos"),
    CA("Câncer"),
    LE("Leão"),
    VI("Virgem"),
    LI("Libra"),
    ES("Escorpião"),
    SG("Sagitário"),
    CP("Capricórnio"),
    AQ("Aquário"),
    PE("Peixes");
	
	private String descricao;
	
	TipoSigno(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public static String getByString(String texto) {
		for (TipoSigno t: TipoSigno.values()) {
			if (t.toString().equalsIgnoreCase(texto)) {
				return t.getDescricao();
			}
		}
		return texto;
	}
	
}
