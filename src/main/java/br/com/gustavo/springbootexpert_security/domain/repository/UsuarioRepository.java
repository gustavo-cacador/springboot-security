package br.com.gustavo.springbootexpert_security.domain.repository;

import br.com.gustavo.springbootexpert_security.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByLogin(String login);
}
