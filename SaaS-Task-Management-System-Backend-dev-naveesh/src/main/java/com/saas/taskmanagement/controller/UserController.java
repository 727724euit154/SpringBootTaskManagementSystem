package com.saas.taskmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saas.taskmanagement.model.User;
import com.saas.taskmanagement.service.UserService;



@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() {
        try{
            User ans=service.getCurrentUser();
            return ResponseEntity.status(HttpStatus.OK).body(ans);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }

    @PutMapping("/me")
    public ResponseEntity<User> updateCurrentUser(@RequestBody User obj){
        try{
            User ans=service.updateCurrentUser(obj);
            return ResponseEntity.status(HttpStatus.OK).body(ans);

        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PutMapping("/me/password")
    public ResponseEntity<User> changePassword(@RequestBody String newPassword){
        try{
            User ans=service.changePassword(newPassword);
            return ResponseEntity.status(HttpStatus.OK).body(ans);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        
    }
    
    @PreAuthorize("hasRole('ADMIN')")

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User obj){
        try{
            User ans=service.createUser(obj);
            return ResponseEntity.status(HttpStatus.CREATED).body(ans);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        try{
            List<User> ans=service.getAllUser();
            return ResponseEntity.status(HttpStatus.OK).body(ans);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    
    }
    @PreAuthorize("hasRole('ADMIN')")

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        try{
            User ans=service.getUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(ans);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable int id,@RequestBody User obj){
        try{
            User ans=service.updateUserById(id,obj);
            return ResponseEntity.status(HttpStatus.OK).body(ans);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/role")
    public ResponseEntity<User> updateRoleById(@PathVariable int id,@RequestParam String role){
        try{
            User current=service.updateRoleById(id,role);
            return ResponseEntity.status(HttpStatus.OK).body(current);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/status")
    public ResponseEntity<User> updateStatusById(@PathVariable int id,@RequestParam Boolean status){
        try{
            User current=service.updateStatusById(id,status);
            return ResponseEntity.status(HttpStatus.OK).body(current);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id){
        
        try{
            String ans=service.deleteUserById(id);

            return ResponseEntity.status(HttpStatus.OK).body(ans);
        }
        catch(Exception e){
            String ans=service.deleteUserById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ans);
        }
    }
}
    
