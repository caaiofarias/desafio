package com.desafio.rest.model;

public class Phone {
	
	private int id;
	private int ddd;
	private String number;
	private String type;
	private int fk;
	
	public Phone(int id, int ddd, String number, String type) {
		super();
		this.id = id;
		this.ddd = ddd;
		this.number = number;
		this.type = type;
	}
	
	public Phone() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getDdd() {
		return ddd;
	}
	
	public void setDdd(int ddd) {
		this.ddd = ddd;
	}
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String string) {
		this.number = string;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public int getFk() {
		return fk;
	}

	public void setFk(int fk) {
		this.fk = fk;
	}
	
}
