package com.portfolio.AC.services;

import com.portfolio.AC.models.Experience;
import com.portfolio.AC.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceService {
    @Autowired
    public ExperienceRepository experienceRepository;

    public List<Experience> getAllExp() throws Exception {
        try {
            return experienceRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Experience> findById(Long id) throws Exception {
        try {
            return experienceRepository.findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Experience> findByUserId(Long id) throws Exception {
        try {
            return experienceRepository.findByUserId(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void saveExperience(Experience exp) throws Exception {
        try {
            experienceRepository.save(exp);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deleteExperience(Long id) throws Exception {
        try {
            experienceRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
