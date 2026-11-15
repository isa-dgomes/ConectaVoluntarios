package br.com.conectavoluntario.dto.response;

public record OrganizacaoResponse(
        Long id,
        String nome,
        String descricao,
        String cnpj,
        String email,
        String telefone,
        String endereco,
        String site
        ) {}
