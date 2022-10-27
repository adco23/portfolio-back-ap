package com.portfolio.AC.repository;

import com.portfolio.AC.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
  List<Skill> findByUserId(Long id);
}
