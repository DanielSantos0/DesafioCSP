package com.example.desafio.desafio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Telefone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String telefone;

	public Telefone() {
		super();
	}

	public Telefone(String telefone) {
		super();
		this.telefone = telefone;
	}

	public long getId() {
		return id;
	}

	public String getTelefone() {
		return telefone;
	}
	
	
}
