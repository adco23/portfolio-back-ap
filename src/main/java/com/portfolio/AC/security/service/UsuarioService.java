package com.portfolio.AC.security.service;

import com.portfolio.AC.security.entity.Usuario;
import com.portfolio.AC.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
  @Autowired
  UsuarioRepository usuarioRepository;

  public Optional<Usuario> getByUsername(String username){
    return usuarioRepository.findByUsername(username);
  }

  public boolean existsByUsername(String username) {
    return usuarioRepository.existsByUsername(username);
  }

  public boolean existsByEmail(String email) {
    return usuarioRepository.existsByEmail(email);
  }

  public void save(Usuario usuario) {
    usuarioRepository.save(usuario);
  }
}
