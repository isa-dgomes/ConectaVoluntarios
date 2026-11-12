package br.com.conectavoluntario.dto.response;

import java.util.Set;

public record VoluntarioResponse(
		
		Long id,
		String nome,
		String email,
		String telefone,
		String cidade,
		String estado,
		Set<String> areasDeInteresse
		) {	

}
