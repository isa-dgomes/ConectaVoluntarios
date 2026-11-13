package br.com.conectavoluntario.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Trata erros de validação dos DTOs
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> tratarErroValidacao(
            MethodArgumentNotValidException ex) {

        // Recupera todas as mensagens de validação que falharam
        List<ErroDetalhe> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> new ErroDetalhe(
                        erro.getField(),
                        erro.getDefaultMessage()))
                .toList();

        // Monta a resposta que será enviada ao cliente
        ErroResposta resposta = new ErroResposta(
                400,
                "Erro de validação",
                erros
        );

        return ResponseEntity.badRequest().body(resposta);
    }


    // Trata erros de regras de negócio lançados pelo Service
    @ExceptionHandler(RegrasDeNegocioException.class)
    public ResponseEntity<ErroResposta> tratarErroRegraDeNegocio(
            RegrasDeNegocioException ex) {

        ErroResposta resposta = new ErroResposta(
                400,
                "Regra de negócio violada",
                List.of(new ErroDetalhe(
                        "geral",
                        ex.getMessage()))
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(resposta);
    }


    // Estrutura padrão da resposta de erro
    public record ErroResposta(
            int status,
            String erro,
            List<ErroDetalhe> mensagens
    ) {}


    // Representa cada erro encontrado
    public record ErroDetalhe(
            String campo,
            String mensagem
    ) {}

}
