//package com.portfolio.AC.models;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "profile")
//public class Profile {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @Column(name = "id")
//  private Long id;
//
//  @Size(min = 2, max = 50, message = "should has min 2 chars and max 50 chars")
//  @Column(name = "first_name")
//  private String first_name;
//
//  @Size(min = 2, max = 50, message = "should has min 2 chars and max 50 chars")
//  @Column(name = "last_name")
//  private String last_name;
//
//  @Size(min = 2, max = 50, message = "should has min 2 chars and max 50 chars")
//  @Column(name = "title")
//  private String title;
//
//  @Size(max = 1500, message = "the about input must be 1500 chars maximum")
//  @Column(name = "about")
//  private String about;
//  @Column(name = "img")
//  private String img;
//}
