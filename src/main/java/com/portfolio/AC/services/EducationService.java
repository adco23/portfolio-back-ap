package com.portfolio.AC.services;

import com.portfolio.AC.models.Education;
import com.portfolio.AC.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService {
    @Autowired
    public EducationRepository educationRepository;

    public List<Education> getAllEducation() throws Exception {
        try {
            return educationRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Education> findById(Long id) throws Exception {
        try {
            return educationRepository.findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void saveEducation(Education education) throws Exception {
        try {
            educationRepository.save(education);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deleteEducation(Long id) throws Exception {
        try {
            educationRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
