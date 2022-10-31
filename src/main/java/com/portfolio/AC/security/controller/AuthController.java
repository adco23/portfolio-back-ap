package com.portfolio.AC.security.controller;

import com.portfolio.AC.security.Dto.JwtDto;
import com.portfolio.AC.security.Dto.LoginUsuario;
import com.portfolio.AC.security.Dto.NuevoUsuario;
import com.portfolio.AC.security.entity.Role;
import com.portfolio.AC.security.entity.Usuario;
import com.portfolio.AC.security.enums.RoleName;
import com.portfolio.AC.security.jwt.JwtProvider;
import com.portfolio.AC.security.service.RoleService;
import com.portfolio.AC.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UsuarioService usuarioService;

  @Autowired
  RoleService roleService;

  @Autowired
  JwtProvider jwtProvider;

  @PostMapping("/new")
  public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<>(new Mensaje("Campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
    }

    if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
        return new ResponseEntity<>(new Mensaje("El usuario ya existe"), HttpStatus.BAD_REQUEST);
    }

    if (usuarioService.existsByUsername(nuevoUsuario.getUsername())) {
      return new ResponseEntity<>(new Mensaje("El usuario ya existe"), HttpStatus.BAD_REQUEST);
    }

    Usuario usuario = new Usuario(
        nuevoUsuario.getName(),
        nuevoUsuario.getUsername(),
        nuevoUsuario.getEmail(),
        passwordEncoder.encode(nuevoUsuario.getPassword())
    );

    Set<Role> roles = new HashSet<>();
    roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());

    if (nuevoUsuario.getRoles().contains("admin")) {
      roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
    }

    usuario.setRoles(roles);
    usuarioService.save(usuario);

    return new ResponseEntity<>(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
    try {
      if (bindingResult.hasErrors()) {
        return new ResponseEntity<>(new Mensaje("Campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
      }

      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          loginUsuario.getUsername(),
          loginUsuario.getPassword()
      ));

      SecurityContextHolder.getContext().setAuthentication(authentication);

      String jwt = jwtProvider.generateToken(authentication);

      UserDetails userDetails = (UserDetails) authentication.getPrincipal();

      JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

      return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    } catch (Exception error) {
      return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
