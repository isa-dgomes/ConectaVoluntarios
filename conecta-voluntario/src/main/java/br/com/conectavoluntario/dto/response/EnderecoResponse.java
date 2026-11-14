package br.com.conectavoluntario.dto.response;

public record EnderecoResponse(
    String cidade,
    String estado,
    String logradouro,
    String numero,
    String cep,
    String bairro
) {}