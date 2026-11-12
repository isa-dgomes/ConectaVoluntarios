package br.com.conectavoluntario.dto.request;

import java.util.Set;

import br.com.conectavoluntario.enums.DiaSemana;
import br.com.conectavoluntario.enums.Periodo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VoluntarioRequest(
		
		@NotBlank @Size(max = 120) String nome,
		@NotBlank @Email @Size(max = 254) String email,
		@NotBlank @Size(min = 8, max = 72) String senha,
		@NotBlank String confirmarSenha,
		@NotBlank @Size(max = 20) String telefone,
		@AssertTrue(message = "E necessario aceitar os termos") boolean aceitouTermos,
		@NotNull @Valid EnderecoRequest endereco,
		String habilidades,
		String experiencia,
		@NotEmpty(message = "Selecione ao menos uma area de interesse") Set<Long> areasIds,
		@NotEmpty(message = "Selecione ao menos um dia") Set<DiaSemana> disponibilidade,
		@NotEmpty(message = "Selecione ao menos um periodo") Set<Periodo> periodos
		
		) {}

/*@valid entra no json aninhado do endereco 
e valida se realmente as informações inseridas 
condizem com endereco request*/


