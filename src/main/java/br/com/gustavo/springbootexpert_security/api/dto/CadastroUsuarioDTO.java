package br.com.gustavo.springbootexpert_security.api.dto;

import br.com.gustavo.springbootexpert_security.domain.entity.Usuario;
import lombok.Data;

import java.util.List;

@Data
public class CadastroUsuarioDTO {

    private Usuario usuario;
    private List<String> permissoes;
}
