package com.saas.taskmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.saas.taskmanagement.model.Project;
import com.saas.taskmanagement.repository.ProjectRepo;

@Service
public class ProjectService {

    private final ProjectRepo projectRepo;

    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public Project createProject(Project p){
        return projectRepo.save(p);
    }

    public List<Project> getAllProjects(){
        return projectRepo.findAll();
    }

    public Optional<Project> getProjectById(Long id){
        return projectRepo.findById(id);
    }

    public Project updateProject(Long id, Project updatedProject){
        return projectRepo.findById(id).map(project -> {
            project.setName(updatedProject.getName());
            project.setDescription(updatedProject.getDescription());
            return projectRepo.save(project);
        }).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public void deleteProject(Long id){
        projectRepo.deleteById(id);
    }










    
}
