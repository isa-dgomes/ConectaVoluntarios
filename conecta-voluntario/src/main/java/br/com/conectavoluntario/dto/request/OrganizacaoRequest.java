package br.com.conectavoluntario.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record OrganizacaoRequest(
        @NotBlank
        @Size(min = 8, max = 72)
        String senha,
        @NotBlank
        String confirmarSenha,
        @NotBlank
        @Size(max = 100)
        String nome,
        @Size(max = 150)
        String razaoSocial,
        @NotBlank
        @Pattern(regexp = "\\d{14}", message = "CNPJ deve ter 14 dígitos")
        String cnpj,
        @NotBlank
        @Email
        @Size(max = 100)
        String email,
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
        @AssertTrue(message = "É necessário aceitar os termos")
        boolean aceitouTermos,
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
