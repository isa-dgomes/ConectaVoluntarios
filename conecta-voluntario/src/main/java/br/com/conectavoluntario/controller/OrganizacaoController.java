package br.com.conectavoluntario.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.conectavoluntario.dto.request.OrganizacaoRequest;
import br.com.conectavoluntario.dto.request.OrganizacaoUpdateRequest;
import br.com.conectavoluntario.dto.response.OrganizacaoResponse;
import br.com.conectavoluntario.service.OrganizacaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/organizacoes")
public class OrganizacaoController {

    private final OrganizacaoService organizacaoService;

    public OrganizacaoController(OrganizacaoService organizacaoService) {
        this.organizacaoService = organizacaoService;
    }
//metodo cadastrar organizacao

    @PostMapping
    public ResponseEntity<OrganizacaoResponse> cadastrar(@Valid @RequestBody OrganizacaoRequest req) {
        OrganizacaoResponse response = organizacaoService.cadastrar(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
//metodo buscar organizacao por id

    @GetMapping("/{id}")
    public ResponseEntity<OrganizacaoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(organizacaoService.buscarPorId(id));
    }
//metodo listar organizacoes

    @GetMapping
    public ResponseEntity<Page<OrganizacaoResponse>> listar(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(organizacaoService.listar(pageable));
    }
//metodo atualizar organizacao

    @PutMapping("/{id}")
    public ResponseEntity<OrganizacaoResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody OrganizacaoUpdateRequest req) {
        return ResponseEntity.ok(organizacaoService.atualizar(id, req));
    }
//metodo remover organizacao

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        organizacaoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
