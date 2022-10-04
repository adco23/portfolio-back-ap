package com.portfolio.AC.services;

import com.portfolio.AC.models.Project;
import com.portfolio.AC.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    public ProjectRepository projectRepository;

    public List<Project> getAllProjects() throws Exception {
        try {
            return projectRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Project> findById(Long id) throws Exception {
        try {
            return projectRepository.findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void saveProject(Project project) throws Exception {
        try {
            projectRepository.save(project);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deleteProject(Long id) throws Exception {
        try {
            projectRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
