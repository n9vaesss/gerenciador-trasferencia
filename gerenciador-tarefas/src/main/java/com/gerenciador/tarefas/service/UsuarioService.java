package com.gerenciador.tarefas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gerenciador.tarefas.entity.Usuario;
import com.gerenciador.tarefas.repository.IRoleRepository;
import com.gerenciador.tarefas.repository.IUsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    private IRoleRepository iRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario salvarUsuario(Usuario usuario) {

        usuario.setRoles(
                usuario.getRoles()
                        .stream()
                        .map(role -> iRoleRepository.findByNome(role.getNome()))
                        .toList());

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return this.iUsuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Usuario usuario) {

        usuario.setRoles(
                usuario.getRoles()
                        .stream()
                        .map(role -> iRoleRepository.findByNome(role.getNome()))
                        .toList());

        Usuario entity = iUsuarioRepository.getReferenceById(usuario.getId());
        usuario.setPassword(entity.getPassword());

        return this.iUsuarioRepository.save(usuario);
    }

    public void excluirUsuario(Usuario usuario) {
        this.iUsuarioRepository.deleteById(usuario.getId());
    }

    public List<Usuario> obtemUsuarios() {
        return this.iUsuarioRepository.findAll();
    }

    public Usuario encontrarUsuario(String username) {

        Optional<Usuario> obj = iUsuarioRepository.findByUsername(username);
        return obj.orElseThrow(() -> new ResourceNotFoundException(username));
    }
}
