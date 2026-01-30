package com.saas.taskmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.taskmanagement.model.Task;
import com.saas.taskmanagement.repository.TaskRepo;

@Service
public class TaskService {

    @Autowired
    private TaskRepo repo;

    public Task createTask(Task task) {
        return repo.save(task);
    }

    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    public Task getTaskById(Long id) {
        Optional<Task> optionalTask = repo.findById(id);
        return optionalTask.orElse(null);
    }

    public Task updateTask(Task task) {
    if (task.getId() == null) {
        return null;
    }

    Optional<Task> optional = repo.findById(task.getId());
    if (optional.isEmpty()) {
        return null;
    }

    Task existing = optional.get();

    existing.setTitle(task.getTitle());
    existing.setDescription(task.getDescription());
    existing.setDueDate(task.getDueDate());

    if (task.getStatus() != null) {
        existing.setStatus(task.getStatus());
    }
    if (task.getPriority() != null) {
        existing.setPriority(task.getPriority());
    }

    return repo.save(existing);
}


    public Boolean deleteTask(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
