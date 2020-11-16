package com.desafio.rest.model;

public class Token {
	
	private int id;
	private String value;
	private int fk;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getFk() {
		return fk;
	}
	public void setFk(int fk) {
		this.fk = fk;
	}
	public Token(String value, int fk) {
		super();
		this.value = value;
		this.fk = fk;
	}
	
}
