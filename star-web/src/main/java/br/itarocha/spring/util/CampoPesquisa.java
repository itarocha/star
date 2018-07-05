package br.itarocha.spring.util;

import java.util.ArrayList;
import java.util.List;

public class CampoPesquisa {

	private String name;
	private String type;
	private String display;
	private List<ItemOpcao> values = new ArrayList<ItemOpcao>();

	public CampoPesquisa(String name, String type, String display) {
		this.name = name;
		this.type = type;
		this.display = display;
	}

	public CampoPesquisa(String name, String type, String display, List<ItemOpcao> values) {
		this(name, type, display);
		this.values = values;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}

	public List<ItemOpcao> getValues() {
		return values;
	}

	public void setValues(List<ItemOpcao> values) {
		this.values = values;
	}
	
}

//new ArrayList<>(Arrays.asList(array))
/*
 
Integer[] spam = new Integer[] { 1, 2, 3 };
Arrays.asList(spam); 
 
 */