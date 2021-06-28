package com.example.desafio.desafio.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

@Entity
public class Contato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String primeiroNome;
	
	@NotNull
	private String ultimoNome;
	
	@NotNull
	private String email;
	
	@NotNull
	@OneToMany
	private List<Telefone> telefones;
	
	public Contato(String primeiroNome, String ultimoNome, String email, List<Telefone> telefones) {
		this.primeiroNome = primeiroNome;
		this.ultimoNome = ultimoNome;
		this.email = email;
		this.telefones = telefones;
		
	}
	
	public void atualizar(String primeiroNome, String ultimoNome, String email, List<Telefone> telefones) {
		this.primeiroNome = primeiroNome;
		this.ultimoNome = ultimoNome;
		this.email = email;
		this.telefones = telefones;
	}
	
	public Contato() {
		super();
	}

	public long getId() {
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

	public List<Telefone> getTelefones() {
		return telefones;
	}
	
}
