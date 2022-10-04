package com.portfolio.AC.controllers;

import com.portfolio.AC.models.Skill;
import com.portfolio.AC.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/skills")
public class SkillController {
  @Autowired
  public SkillService skillService;

  @GetMapping("")
  public ResponseEntity<?> skillList(){
    try {
      return ResponseEntity.status(HttpStatus.OK).body(skillService.skillList());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @GetMapping("/skill/{id}")
  public ResponseEntity<?> getSkillById(@PathVariable Long id){
    try {
      Optional<Skill> optionalSkill = skillService.getSkillById(id);
      if (!optionalSkill.isPresent()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"skill not found\"}");
      }
      return ResponseEntity.status(HttpStatus.OK).body(optionalSkill.get());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @PostMapping("")
  public ResponseEntity<?> saveSkill(@Valid @RequestBody Skill skill){
    try {
      skillService.saveSkill(skill);
      return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"skill created successfully\"}");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PutMapping("/skill/{id}")
  public ResponseEntity<?> updateSkill(@PathVariable Long id, @RequestBody Skill skill){
    try {
//            User currentUser = userService.getUserById(id);
//            currentUser.setEmail(user.getEmail());
//            currentUser.setPassword(user.getPassword());
//            currentUser.setFirst_name(user.getFirst_name());
//            currentUser.setLast_name(user.getLast_name());
//            currentUser.setTitle(user.getTitle());
//            currentUser.setAbout(user.getAbout());
//            currentUser.setImg(user.getImg());
//            userService.saveUser(currentUser);
//            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"user changed successfully\"}");

      Optional<Skill> optionalSkill = skillService.getSkillById(id);
      if (!optionalSkill.isPresent()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"skill not found\"}");
      }

      skill.setId(optionalSkill.get().getId());
      skillService.saveSkill(skill);
      return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"skill changed successfully\"}");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @DeleteMapping("/skill/{id}")
  public ResponseEntity<?> deleteSkill(@PathVariable Long id){
    try {
      skillService.deleteSkill(id);
      return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"skill deleted successfully\"}");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
