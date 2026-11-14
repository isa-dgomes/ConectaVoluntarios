package br.com.conectavoluntario.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.conectavoluntario.dto.response.EnderecoResponse;
import br.com.conectavoluntario.dto.response.VoluntarioResponse;
import br.com.conectavoluntario.entity.AreaAtuacao;
import br.com.conectavoluntario.entity.Voluntario;

@Component
public class VoluntarioMapper {
	
	public VoluntarioResponse toResponse(Voluntario v) {
		
		EnderecoResponse endereco = new EnderecoResponse(
	            v.getEndereco().getCidade(),
	            v.getEndereco().getEstado(),
	            v.getEndereco().getLogradouro(),
	            v.getEndereco().getNumero(),
	            v.getEndereco().getCep(),
	            v.getEndereco().getBairro()
	        );
		
		return new VoluntarioResponse(
				
				v.getIdUsuario(),
				v.getNome(),
				v.getEmail(),
				v.getTelefone(),
				endereco,
				v.getAreasDeInteresse().stream()
					.map(AreaAtuacao::getNomeArea)
					.collect(Collectors.toSet()),
				new HashSet<>(v.getDisponibilidade()),
				new HashSet<>(v.getPeriodo())
				
				);
	}
	
	public List<VoluntarioResponse> toResponse(List<Voluntario> voluntarios) {
	    return voluntarios.stream()
	            .map(this::toResponse)
	            .toList();
	}

}
