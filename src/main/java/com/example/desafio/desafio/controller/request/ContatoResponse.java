package com.example.desafio.desafio.controller.request;

import java.util.List;

import com.sun.istack.NotNull;

public class ContatoResponse {
	
	private Long id;
	
	private String primeiroNome;
	
	private String ultimoNome;
	
	private String email;
	
	private List<String> telefone;

	public ContatoResponse(Long id, String primeiroNome, String ultimoNome, String email, List<String> telefone) {
		super();
		this.id = id;
		this.primeiroNome = primeiroNome;
		this.ultimoNome = ultimoNome;
		this.email = email;
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public String getEmail() {
		return email;
	}

	public List<String> getTelefone() {
		return telefone;
	}
	
}
