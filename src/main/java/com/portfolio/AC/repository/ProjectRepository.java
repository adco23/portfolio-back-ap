package com.portfolio.AC.repository;

import com.portfolio.AC.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
  List<Project> findByUserId(Long id);
}
