package br.com.conectavoluntario.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;


public record EnderecoRequest(
		@NotBlank @Size(max = 100) String cidade,
		@NotBlank @Size(max = 50) String estado,
		@NotBlank @Size(max = 150) String logradouro,
		@NotBlank @Size(max = 20) String numero,
		@Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP deve ter 8 digitos") String cep,
		@Size(max = 100) String bairro
		
		) { }
