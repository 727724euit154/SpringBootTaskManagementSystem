package com.saas.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saas.taskmanagement.model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    
}
