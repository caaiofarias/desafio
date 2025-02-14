package com.desafio.rest.model;

import java.util.List;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String autorizado;
    private List<Phone> phones;

    public User(int id, String name, String email, String password, String token, List<Phone> phones) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.autorizado = token;
        this.phones = phones;
        this.setToken(token);
    }

    public User(String name, String email, String password) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public User(String name, String email, String password, String autorizado) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.email = autorizado;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public String getToken() {
		return autorizado;
	}

	public void setToken(String token) {
		this.autorizado = token;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return id == other.id;
    }


}
