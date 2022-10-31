package com.portfolio.AC.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "https://portfolio-front-adco.vercel.app", "http://localhost:4200"})
@RestController
@RequestMapping("/")
public class StartController {
  @GetMapping("")
  public ResponseEntity<?> welcome(){
    try {
      return ResponseEntity.status(HttpStatus.OK).body("Welcome to portfolio");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}
