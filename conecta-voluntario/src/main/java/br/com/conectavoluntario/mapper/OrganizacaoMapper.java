package br.com.conectavoluntario.mapper;

import java.time.Instant;

import org.springframework.stereotype.Component;

import br.com.conectavoluntario.dto.request.OrganizacaoRequest;
import br.com.conectavoluntario.dto.response.OrganizacaoResponse;
import br.com.conectavoluntario.entity.Organizacao;

@Component
public class OrganizacaoMapper {

    // DTO de entrada -> Entidade (usado no criar)
    public Organizacao ToEntidade(OrganizacaoRequest dto) {
        Organizacao org = new Organizacao();
        org.setNome(dto.nome());
        org.setEmail(dto.email());
        org.setSenhaHash(dto.senha());
        org.setTelefone(dto.telefone());
        org.setAceitouTermos(dto.aceitouTermos());
        org.setDataAceiteTermos(Instant.now());
        org.setRazaoSocial(dto.razaoSocial());
        org.setCnpj(dto.cnpj());
        org.setDescricao(dto.descricao());
        org.setTipoOrganizacao(dto.tipoOrganizacao());
        org.setResponsavel(dto.responsavel());
        return org;
    }

    // Entidade -> DTO de resposta (o antigo paraResponse)
    public OrganizacaoResponse ToResponse(Organizacao org) {
        return new OrganizacaoResponse(
                org.getIdUsuario(),
                org.getNome(),
                org.getEmail(),
                org.getTelefone(),
                org.getRazaoSocial(),
                org.getCnpj(),
                org.getDescricao(),
                org.getTipoOrganizacao()
        );
    }
}
