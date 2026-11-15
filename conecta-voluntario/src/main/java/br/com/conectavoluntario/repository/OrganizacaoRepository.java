package br.com.conectavoluntario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.conectavoluntario.entity.Organizacao;

public interface OrganizacaoRepository extends JpaRepository<Organizacao, Long> {

    boolean existsByCnpj(String cnpj);
}
