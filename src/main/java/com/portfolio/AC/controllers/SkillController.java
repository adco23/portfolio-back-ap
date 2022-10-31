package com.portfolio.AC.controllers;

import com.portfolio.AC.models.Experience;
import com.portfolio.AC.models.Skill;
import com.portfolio.AC.models.User;
import com.portfolio.AC.services.SkillService;
import com.portfolio.AC.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = { "https://portfolio-front-adco.vercel.app", "http://localhost:4200"})
@RestController
@RequestMapping("/api/v1/skills")
public class SkillController {
  @Autowired
  public SkillService skillService;

  @Autowired
  public UserService userService;

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

  @GetMapping("/user/{user_id}")
  public ResponseEntity<?> getSkillsByUser(@PathVariable Long user_id) {
    try {
      if (!userService.existUserById(user_id)) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"user not found\"}");
      }

      List<Skill> skills = skillService.findByUserId(user_id);

      return ResponseEntity.status(HttpStatus.OK).body(skills);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("")
  public ResponseEntity<?> saveSkill(@Valid @RequestBody Skill skill){
    try {
      skillService.saveSkill(skill);
      return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"skill created successfully\"}");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/user/{user_id}")
  public ResponseEntity<?> createSkill (
      @PathVariable(value = "user_id") Long user_id,
      @RequestBody Skill skillReq
  ) throws Exception {
    try {
      Optional<User> optionalUser = userService.getUserById(user_id);
      if (!optionalUser.isPresent()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"user not found\"}");
      }
      skillReq.setUser(optionalUser.get());
      skillService.saveSkill(skillReq);
      return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"skill created successfully\"}");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/user/{user_id}/skill/{id}")
  public ResponseEntity<?> updateSkill(@RequestBody Skill skill, @PathVariable Long id, @PathVariable Long user_id){
    try {
//            Experience current = experienceService.findById(id);
//            current.setPosition(exp.getPosition());
//            current.setDescription(exp.getDescription());
//            current.setCompany(exp.getCompany());
//            current.setCompany_img(exp.getCompany_img());
//            current.setStart_date(exp.getStart_date());
//            current.setFinish_date((exp.getFinish_date()));
//            experienceService.saveExperience(current);
      Optional<User> optionalUser = userService.getUserById(user_id);
      if (!optionalUser.isPresent()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"user not found\"}");
      }

      Optional<Skill> optionalSkill = skillService.getSkillById(id);
      if (!optionalSkill.isPresent()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"skill not found\"}");
      }
      skill.setUser(optionalUser.get());
      skill.setId(optionalSkill.get().getId());
      skillService.saveSkill(skill);

      return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"skill changed successfully\"}");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @PreAuthorize("hasRole('ADMIN')")
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
