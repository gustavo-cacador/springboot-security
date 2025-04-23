package br.com.gustavo.springbootexpert_security.domain.repository;

import br.com.gustavo.springbootexpert_security.domain.entity.UsuarioGrupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo, String> {
}
