package com.portfolio.AC.security.entity;

import com.portfolio.AC.security.enums.RoleName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull
  @Enumerated(EnumType.STRING)
  private RoleName roleName;

}
