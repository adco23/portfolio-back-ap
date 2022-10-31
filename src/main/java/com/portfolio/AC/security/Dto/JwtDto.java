package com.portfolio.AC.security.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtDto {
  private String token;
  private final String bearer = "Bearer";
  private String username;
  private Collection<? extends GrantedAuthority> authorities;
}
