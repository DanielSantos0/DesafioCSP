package com.example.desafio.desafio.dominio;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.desafio.desafio.model.Contato;
import com.example.desafio.desafio.repository.ContatoRepository;

@Service
public class FiltrarContatos {
	
	private ContatoRepository contatoRepository;
	
	public List<Contato> filtrarPorNome(String PrimeiroNome) {
	
		return contatoRepository.findByPrimeiroNomeContainingIgnoreCase(PrimeiroNome);
	}
	
	public List<Contato> filtrarPorEmail(String email) {
		
		return contatoRepository.findByEmailContainingIgnoreCase(email);
	}

}
