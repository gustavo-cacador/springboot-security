package br.com.gustavo.springbootexpert_security.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String login; // login -> cpf, email, nome de usuario
    private String senha;
    private String nome;

    @Transient // ignora o atributo para o banco de dados
    private List<String> permissoes;

}
