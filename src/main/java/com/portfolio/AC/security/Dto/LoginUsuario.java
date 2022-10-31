package com.portfolio.AC.security.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginUsuario {
  @NotNull
  private String username;
  @NotNull
  private String password;
}
