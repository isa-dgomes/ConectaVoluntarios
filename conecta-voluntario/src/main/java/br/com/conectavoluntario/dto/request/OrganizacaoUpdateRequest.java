package br.com.conectavoluntario.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OrganizacaoUpdateRequest(
        @NotBlank
        @Size(max = 100)
        String nome,
        @Size(max = 150)
        String razaoSocial,
        @NotBlank
        @Size(max = 20)
        String telefone,
        @NotBlank
        @Size(max = 200)
        String descricao,
        @NotBlank
        @Size(max = 100)
        String responsavel,
        String tipoOrganizacao,
        // endereço
        @NotBlank
        String cidade,
        @NotBlank
        String estado,
        @NotBlank
        String logradouro,
        @NotBlank
        String numero,
        String cep,
        String bairro
        ) {}
