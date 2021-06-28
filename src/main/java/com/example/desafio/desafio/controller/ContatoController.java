package com.example.desafio.desafio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.desafio.desafio.controller.request.ContatoRequest;
import com.example.desafio.desafio.controller.request.ContatoResponse;
import com.example.desafio.desafio.model.Contato;
import com.example.desafio.desafio.model.Telefone;
import com.example.desafio.desafio.repository.ContatoRepository;
import com.example.desafio.desafio.repository.TelefoneRepository;

@RestController
@RequestMapping("/contatos")
@CrossOrigin("*")
public class ContatoController {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private TelefoneRepository telefoneRepository;
		
	@GetMapping
	public ResponseEntity<List<ContatoResponse>> listarTodosOsContatos(@RequestParam(name = "primeiroNome", required = false) String primeiroNome, 
																	   @RequestParam(name = "email", required = false) String email) {		
						
		List<Contato> contatosConsultados = new ArrayList<Contato>();
		
		if (primeiroNome != null) {
			contatosConsultados = contatoRepository.findByPrimeiroNomeContainingIgnoreCase(primeiroNome);
		} else if (email != null) {
			contatosConsultados = contatoRepository.findByEmailContainingIgnoreCase(email);
		}
		else {
			contatosConsultados = contatoRepository.findAll();
		}
	
		List<ContatoResponse> contatosResponse = new ArrayList<ContatoResponse>();
		
		for (Contato contato : contatosConsultados) {
					
			List<String> telefonesDoContato = converterListaDeTelefonesParaListaDeString(contato.getTelefones());
					
			ContatoResponse contatoResponse = new ContatoResponse(contato.getId(), contato.getPrimeiroNome(), contato.getUltimoNome(), contato.getEmail(), telefonesDoContato);
			
			contatosResponse.add(contatoResponse);
		}
		
		return ResponseEntity.ok(contatosResponse);
	}
	
	private List<String> converterListaDeTelefonesParaListaDeString(List<Telefone> telefones) {
		List<String> telefonesDoContato = new ArrayList<>();
		
		for (Telefone telefone : telefones) {
			telefonesDoContato.add(telefone.getTelefone());
		}
		
		return telefonesDoContato;
	}
	
	@PostMapping
	public ResponseEntity<ContatoResponse> criarContato(@RequestBody ContatoRequest contatoRequest) {
		
		List<Telefone> telefones = new ArrayList<Telefone>();
		
		for (String telefone : contatoRequest.getTelefones()) {
			
			Telefone novoTelefone = new Telefone(telefone);
			
			telefones.add(novoTelefone);
		}
		
		telefoneRepository.saveAll(telefones);
		
		Contato contato = new Contato(contatoRequest.getPrimeiroNome(), contatoRequest.getUltimoNome(), contatoRequest.getEmail(), telefones);
		
		contatoRepository.save(contato);
		
		List<String> tel = new ArrayList<String>();
		
		for (String numeroTelefone : contatoRequest.getTelefones() ) {
			
			String novoTel = new String(numeroTelefone);
			
			tel.add(novoTel);
		}
				
		ContatoResponse contatoResponse = new ContatoResponse(contato.getId(), contato.getPrimeiroNome(), contato.getUltimoNome(), contato.getEmail(), tel);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(contatoResponse);
	}
	
	@DeleteMapping("/{id}")
	public void deletarContato(@PathVariable long id) {
		contatoRepository.deleteById(id); 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ContatoResponse> atualizarContato(@PathVariable Long id, @RequestBody ContatoRequest contatoRequest) {
		
		Optional<Contato> contatoOptional = contatoRepository.findById(id);
		
		Contato contato = contatoOptional.get();
		
		List<Telefone> telefones = new ArrayList<Telefone>();
		
		for (String telefone : contatoRequest.getTelefones()) {
			
			Telefone novoTelefone = new Telefone(telefone);
			
			telefones.add(novoTelefone);
		}			
		
		telefoneRepository.saveAll(telefones);
				
		contato.atualizar(contatoRequest.getPrimeiroNome(), contatoRequest.getUltimoNome(), contatoRequest.getEmail(), telefones);
		
		contatoRepository.save(contato);
		
		List<String> tel = new ArrayList<String>();
		
		for (String numeroTelefone : contatoRequest.getTelefones() ) {
			
			String novoTel = new String(numeroTelefone);
			
			tel.add(novoTel);
		}
		
		ContatoResponse contatoResponse = new ContatoResponse(contato.getId(), contato.getPrimeiroNome(), contato.getUltimoNome(), contato.getEmail(), tel);
		
		return ResponseEntity.status(HttpStatus.OK).body(contatoResponse);
	}

}  
