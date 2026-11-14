package br.com.conectavoluntario.dto.response;

import java.util.Set;

import br.com.conectavoluntario.enums.DiaSemana;
import br.com.conectavoluntario.enums.Periodo;

public record VoluntarioResponse(
		
		Long id,
		String nome,
		String email,
		String telefone,
		EnderecoResponse endereco,
		Set<String> areasDeInteresse,
		Set<DiaSemana> disponibilidade,
	    Set<Periodo> periodos
		) {	

}
