package com.portfolio.AC.controllers;

import com.portfolio.AC.models.Experience;
import com.portfolio.AC.models.User;
import com.portfolio.AC.services.ExperienceService;
import com.portfolio.AC.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = { "https://portfolio-front-adco.vercel.app", "http://localhost:4200"})
@RestController
@RequestMapping("/api/v1/experience")
public class ExperienceController {
    @Autowired
    public ExperienceService experienceService;

    @Autowired
    public UserService userService;

    @GetMapping("")
    public ResponseEntity<?> expList(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(experienceService.getAllExp());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/exp/{id}")
    public ResponseEntity<?> getExp(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(experienceService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<?> getExpByUser(@PathVariable Long user_id) {
        try {
            if (!userService.existUserById(user_id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"user not found\"}");
            }
//            return ResponseEntity.status(HttpStatus.OK).body(experienceService.findById(id));
            List<Experience> experiences = experienceService.findByUserId(user_id);

            return ResponseEntity.status(HttpStatus.OK).body(experiences);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> saveExp(@RequestBody Experience exp){
        try {
            Optional<User> optionalUser = userService.getUserById(exp.getUser().getId());
            if (!optionalUser.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"user not found\"}");
            }
            exp.setUser(optionalUser.get());
            experienceService.saveExperience(exp);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"experience created successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/user/{user_id}")
    public ResponseEntity<?> createExp(@PathVariable(value = "user_id") Long user_id,
                                                 @RequestBody Experience experienceReq) throws Exception {
        try {
            Optional<User> optionalUser = userService.getUserById(user_id);
            if (!optionalUser.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"user not found\"}");
            }
            experienceReq.setUser(optionalUser.get());
            experienceService.saveExperience(experienceReq);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"experience created successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/user/{user_id}/exp/{id}")
    public ResponseEntity<?> updateExp(@RequestBody Experience exp, @PathVariable Long id, @PathVariable Long user_id){
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

            Optional<Experience> optionalExperience = experienceService.findById(id);
            if (!optionalExperience.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"experience not found\"}");
            }
            exp.setUser(optionalUser.get());
            exp.setId(optionalExperience.get().getId());
            experienceService.saveExperience(exp);

            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"experience changed successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/exp/{id}")
    public ResponseEntity<?> deleteExp(@PathVariable Long id){
        try {
            experienceService.deleteExperience(id);
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"experience deleted successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
