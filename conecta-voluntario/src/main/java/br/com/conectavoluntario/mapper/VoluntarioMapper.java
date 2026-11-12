package br.com.conectavoluntario.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.conectavoluntario.dto.response.VoluntarioResponse;
import br.com.conectavoluntario.entity.AreaAtuacao;
import br.com.conectavoluntario.entity.Voluntario;

@Component
public class VoluntarioMapper {
	
	public VoluntarioResponse toResponse(Voluntario v) {
		return new VoluntarioResponse(
				
				v.getIdUsuario(),
				v.getNome(),
				v.getEmail(),
				v.getTelefone(),
				v.getEndereco().getCidade(),
				v.getEndereco().getEstado(),
				v.getAreasDeInteresse().stream()
					.map(AreaAtuacao::getNomeArea)
					.collect(Collectors.toSet())
				
				);
	}

}
