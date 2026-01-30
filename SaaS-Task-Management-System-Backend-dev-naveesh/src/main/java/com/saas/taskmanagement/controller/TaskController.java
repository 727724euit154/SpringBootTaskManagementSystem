package com.saas.taskmanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saas.taskmanagement.model.Task;
import com.saas.taskmanagement.service.TaskService;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    TaskService s;

    public TaskController(TaskService s) {
        this.s = s;
    }



    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        Task tas = s.createTask(task);
        if(tas !=null){
            return new ResponseEntity<>(tas, HttpStatus.CREATED);
        }
        return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .build();

    }
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = s.getAllTasks();
        if(!tasks.isEmpty()) return new ResponseEntity<>(tasks, HttpStatus.OK);
        return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        Task task = s.getTaskById(id);
        if(task != null) return new ResponseEntity<>(task, HttpStatus.OK);
        return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .build();
    }
    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task task){
        Task tas = s.updateTask(task);
        if(tas !=null){
            return new ResponseEntity<>(tas, HttpStatus.OK);
        }
        return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable Long id){
        Boolean deleted = s.deleteTask(id);
        if(deleted) return new ResponseEntity<>(deleted, HttpStatus.OK);
        return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .build();
    }
}