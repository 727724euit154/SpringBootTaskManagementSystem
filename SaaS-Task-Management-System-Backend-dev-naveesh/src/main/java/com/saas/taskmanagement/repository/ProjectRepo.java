package com.saas.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saas.taskmanagement.model.Project;

@Repository
public interface  ProjectRepo extends JpaRepository<Project, Long> {
    
}
