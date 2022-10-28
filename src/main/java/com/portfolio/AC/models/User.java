package com.portfolio.AC.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
  @NotEmpty(message = "Email cannot be empty")
  @Column(unique = true)
  private String email;

  @NotNull
  @Size(min = 6, message = "must be at least 6 characters long")
  private String password;

  @Size(min = 2, max = 50, message = "should has min 2 chars and max 50 chars")
  private String first_name;

  @Size(min = 2, max = 50, message = "should has min 2 chars and max 50 chars")
  private String last_name;

  @Size(min = 2, max = 50, message = "should has min 2 chars and max 50 chars")
  private String title;

  @Size(max = 1500, message = "the about input must be 1500 chars maximum")
  private String about;
  private String img;

  // --- relationships ---
//  @OneToMany(cascade = CascadeType.ALL)
//  @JoinColumn(name = "user_id")
//  private List<Experience> experiences;

  //@OneToMany(cascade = CascadeType.ALL)
  //@JoinColumn(name = "user_id")
  //private List<Education> educations;

//  @OneToMany(cascade = CascadeType.ALL)
//  @JoinColumn(name = "user_id")
//  private List<Project> projects;

//  @OneToOne(cascade = CascadeType.ALL)
//  @JoinColumn(name = "profile_id", referencedColumnName = "id")
//  private Profile profile;

  // --- constructors ---

}
