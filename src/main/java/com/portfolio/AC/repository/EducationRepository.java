package com.portfolio.AC.repository;

import com.portfolio.AC.models.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
  List<Education> findByUserId(Long id);
}
