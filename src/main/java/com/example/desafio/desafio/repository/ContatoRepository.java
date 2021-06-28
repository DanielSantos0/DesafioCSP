package com.example.desafio.desafio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.desafio.desafio.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
	
	public Optional<Contato> findById(Long id);
	
	public List<Contato> findByPrimeiroNomeContainingIgnoreCase(String primeiroNome);
	
	public List<Contato> findByEmailContainingIgnoreCase(String email);
}
