package br.com.gustavo.springbootexpert_security.domain.service;

import br.com.gustavo.springbootexpert_security.domain.entity.Grupo;
import br.com.gustavo.springbootexpert_security.domain.entity.Usuario;
import br.com.gustavo.springbootexpert_security.domain.entity.UsuarioGrupo;
import br.com.gustavo.springbootexpert_security.domain.repository.GrupoRepository;
import br.com.gustavo.springbootexpert_security.domain.repository.UsuarioGrupoRepository;
import br.com.gustavo.springbootexpert_security.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final GrupoRepository grupoRepository;

    private final UsuarioGrupoRepository usuarioGrupoRepository;

    private final PasswordEncoder passwordEncoder;

    // aqui salvamos um novo usuário
    @Transactional
    public Usuario salvar(Usuario usuario, List<String> grupos) {
        // criptografando senha
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        usuarioRepository.save(usuario);

        // depois associamos esse usuário a grupos existentes, pelo nome do grupo fornecido (findByNome)
        List<UsuarioGrupo> listaUsuarioGrupo = grupos.stream().map(nomeGrupo -> {
            Optional<Grupo> possivelGrupo = grupoRepository.findByNome(nomeGrupo);
            // aqui usamos o isPresent para caso ache esse grupo, cria uma instancia de UsuarioGrupo associando o usuário ao grupo
            if (possivelGrupo.isPresent()) {
                Grupo grupo = possivelGrupo.get();
                return new UsuarioGrupo(usuario, grupo);
            }
            return null;
        })
                .filter(grupo -> grupo != null) // aqui removemos os valores null da lista
                .collect(Collectors.toList());

        usuarioGrupoRepository.saveAll(listaUsuarioGrupo);

        return usuario;
    }
}
