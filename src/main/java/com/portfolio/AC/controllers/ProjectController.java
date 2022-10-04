package com.portfolio.AC.controllers;

import com.portfolio.AC.models.Education;
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
import java.util.Optional;

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
