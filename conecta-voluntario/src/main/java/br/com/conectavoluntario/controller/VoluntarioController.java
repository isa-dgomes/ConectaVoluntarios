package br.com.conectavoluntario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.conectavoluntario.dto.request.VoluntarioRequest;
import br.com.conectavoluntario.dto.response.VoluntarioResponse;
import br.com.conectavoluntario.service.VoluntarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/voluntarios")
public class VoluntarioController {
	
	private final VoluntarioService voluntarioService;
	
	
	public VoluntarioController(VoluntarioService voluntarioService) {
		this.voluntarioService = voluntarioService;
	}
	
	@PostMapping
	public ResponseEntity<VoluntarioResponse> cadastrar(@Valid @RequestBody VoluntarioRequest req){
		
		VoluntarioResponse resposta = voluntarioService.cadastrar(req);
		return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VoluntarioResponse> buscarPorId(@PathVariable Long id){
		
		VoluntarioResponse buscadoPorId =voluntarioService.buscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(buscadoPorId);
	}
	
	



}
