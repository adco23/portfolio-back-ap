package com.portfolio.AC.security.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class NuevoUsuario {
  private String name;
  private String username;
  private String email;
  private String password;
  private Set<String> roles = new HashSet<>();
}
