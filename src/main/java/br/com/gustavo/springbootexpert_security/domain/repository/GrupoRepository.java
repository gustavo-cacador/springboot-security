package br.com.gustavo.springbootexpert_security.domain.repository;

import br.com.gustavo.springbootexpert_security.domain.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, String> {
}
