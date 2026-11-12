package br.com.conectavoluntario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.conectavoluntario.entity.Voluntario;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long>{

}
