package br.com.conectavoluntario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.conectavoluntario.entity.Oportunidade;

public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long> {
}
