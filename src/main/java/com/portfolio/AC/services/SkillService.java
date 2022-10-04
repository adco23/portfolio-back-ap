package com.portfolio.AC.services;

import com.portfolio.AC.models.Skill;
import com.portfolio.AC.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {
  @Autowired
  public SkillRepository skillRepository;

  public List<Skill> skillList() throws Exception {
    try {
      return skillRepository.findAll();
    } catch(Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public void saveSkill(Skill skill) throws Exception {
    try {
      skillRepository.save(skill);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public Optional<Skill> getSkillById(Long id) throws Exception {
    try {
      return skillRepository.findById(id);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public void deleteSkill(Long id) throws Exception {
    try {
      skillRepository.deleteById(id);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }
}
