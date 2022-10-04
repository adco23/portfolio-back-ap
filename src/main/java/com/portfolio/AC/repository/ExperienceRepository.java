package com.portfolio.AC.repository;

import com.portfolio.AC.models.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
  List<Experience> findByUserId(Long id);
}
