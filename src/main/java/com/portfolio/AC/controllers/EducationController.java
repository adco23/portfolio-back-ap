package com.portfolio.AC.controllers;

import com.portfolio.AC.models.Education;
import com.portfolio.AC.models.User;
import com.portfolio.AC.services.EducationService;
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
@RequestMapping("/api/v1/education")
public class EducationController {
    @Autowired
    public EducationService educationService;

    @Autowired
    public UserService userService;

    @GetMapping("")
    public ResponseEntity<?> educationList(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(educationService.getAllEducation());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/edu/{id}")
    public ResponseEntity<?> getEducation(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(educationService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<?> getEduByUser(@PathVariable Long user_id) {
        try {
            if (!userService.existUserById(user_id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"user not found\"}");
            }

            List<Education> educations = educationService.findByUserId(user_id);

            return ResponseEntity.status(HttpStatus.OK).body(educations);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> saveEducation(@Valid @RequestBody Education education){
        try {
            Optional<User> optionalUser = userService.getUserById(education.getUser().getId());
            if (!optionalUser.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"user not found\"}");
            }
            education.setUser(optionalUser.get());
            educationService.saveEducation(education);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"education created successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/user/{user_id}")
    public ResponseEntity<?> createEducation(@PathVariable(value = "user_id") Long user_id,
                                       @RequestBody Education educationReq) throws Exception {
        try {
            Optional<User> optionalUser = userService.getUserById(user_id);
            if (!optionalUser.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"user not found\"}");
            }
            educationReq.setUser(optionalUser.get());
            educationService.saveEducation(educationReq);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"education created successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/user/{user_id}/edu/{id}")
    public ResponseEntity<?> updateEducation(@RequestBody Education education, @PathVariable Long id, @PathVariable Long user_id){
        try {
            Optional<User> optionalUser = userService.getUserById(user_id);
            if (!optionalUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"user not found\"}");
            }

            Optional<Education> optionalEducation = educationService.findById(id);
            if (!optionalEducation.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"education not found\"}");
            }
            education.setUser(optionalUser.get());
            education.setId(optionalEducation.get().getId());
            educationService.saveEducation(education);

            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"education changed successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/edu/{id}")
    public ResponseEntity<?> deleteEducation(@PathVariable Long id){
        try {
            educationService.deleteEducation(id);
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"education deleted successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
