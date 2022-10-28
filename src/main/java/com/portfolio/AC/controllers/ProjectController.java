package com.portfolio.AC.controllers;

import com.portfolio.AC.models.Education;
import com.portfolio.AC.models.Experience;
import com.portfolio.AC.models.Project;
import com.portfolio.AC.models.User;
import com.portfolio.AC.services.EducationService;
import com.portfolio.AC.services.ProjectService;
import com.portfolio.AC.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    @Autowired
    public ProjectService projectService;

    @Autowired
    public UserService userService;

    @GetMapping("")
    public ResponseEntity<?> projectList(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(projectService.getAllProjects());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<?> getProject(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(projectService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<?> getProjectsByUser(@PathVariable Long user_id) {
        try {
            if (!userService.existUserById(user_id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"user not found\"}");
            }
//            return ResponseEntity.status(HttpStatus.OK).body(experienceService.findById(id));
            List<Project> projects = projectService.findByUserId(user_id);

            return ResponseEntity.status(HttpStatus.OK).body(projects);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> saveProject(@RequestBody Project project){
        try {
            Optional<User> optionalUser = userService.getUserById(project.getUser().getId());
            if (!optionalUser.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"user not found\"}");
            }
            project.setUser(optionalUser.get());
            projectService.saveProject(project);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"project created successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/user/{user_id}")
    public ResponseEntity<?> createProject(
        @PathVariable(value = "user_id") Long user_id,
        @RequestBody Project projectReq) throws Exception {
        try {
            Optional<User> optionalUser = userService.getUserById(user_id);
            if (!optionalUser.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"user not found\"}");
            }
            projectReq.setUser(optionalUser.get());
            projectService.saveProject(projectReq);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"project created successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/project/{id}")
    public ResponseEntity<?> updateProject(@RequestBody Project project, @PathVariable Long id){
        try {
            Optional<User> optionalUser = userService.getUserById(project.getUser().getId());
            if (!optionalUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"user not found\"}");
            }

            Optional<Project> optionalProject = projectService.findById(id);
            if (!optionalProject.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"project not found\"}");
            }
            project.setUser(optionalUser.get());
            project.setId(optionalProject.get().getId());
            projectService.saveProject(project);

            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"project changed successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/user/{user_id}/proj/{id}")
    public ResponseEntity<?> updateExp(@RequestBody Project project, @PathVariable Long id, @PathVariable Long user_id){
        try {
            Optional<User> optionalUser = userService.getUserById(user_id);
            if (!optionalUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"user not found\"}");
            }

            Optional<Project> optionalProject = projectService.findById(id);
            if (!optionalProject.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"project not found\"}");
            }
            project.setUser(optionalUser.get());
            project.setId(optionalProject.get().getId());
            projectService.saveProject(project);

            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\":\"project changed successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/project/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id){
        try {
            projectService.deleteProject(id);
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"project deleted successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
