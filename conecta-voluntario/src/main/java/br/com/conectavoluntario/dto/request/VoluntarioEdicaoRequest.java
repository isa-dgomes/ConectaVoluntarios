
package br.com.conectavoluntario.dto.request;

import java.util.Set;

import br.com.conectavoluntario.enums.DiaSemana;
import br.com.conectavoluntario.enums.Periodo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VoluntarioEdicaoRequest(

		@NotBlank @Size(max = 120) String nome,
		@NotBlank @Email @Size(max = 254) String email,
		
		// Senha opcional na edição
		@Size(min = 8, max = 72)String senha,
		String confirmarSenha,
		@NotBlank @Size(max = 20) String telefone,
		@NotNull @Valid EnderecoEdicaoRequest endereco,
		String habilidades,
		String experiencia,
		@NotEmpty Set<Long> areasIds,
		@NotEmpty Set<DiaSemana> disponibilidade,
		@NotEmpty Set<Periodo> periodos

) {}
