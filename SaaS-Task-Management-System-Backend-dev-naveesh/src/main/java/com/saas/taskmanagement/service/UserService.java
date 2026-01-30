package com.saas.taskmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.saas.taskmanagement.model.Role;
import com.saas.taskmanagement.model.User;
import com.saas.taskmanagement.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    
    public User getCurrentUser(){
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        return userRepo.findByUsername(username);
    }
    public User updateCurrentUser(User obj){
        User current=getCurrentUser();
        current.setFullname(obj.getFullname());
        current.setEmail(obj.getEmail());
        
        return userRepo.save(current);
        }
    public User changePassword(String newPassword) {
        User current=getCurrentUser();
        current.setPassword(newPassword);
        return userRepo.save(current);
       
    }
    public User createUser(User obj) {
        return userRepo.save(obj);
    }
    public List<User> getAllUser() {
        return userRepo.findAll();
    }
    public User getUserById(int id) {
        return userRepo.findById(id).get();
    }
    public User updateUserById(int id, User obj) {
       User current=getUserById(id);
       current.setFullname(obj.getFullname());
       current.setEmail(obj.getEmail());
         
       return userRepo.save(current);
    }
    public User updateRoleById(int id, String role) {
        User current=getUserById(id);
        current.setRole(Role.valueOf(role.toUpperCase()));
        return userRepo.save(current);
    }
    public User updateStatusById(int id, Boolean status) {
        User current=getUserById(id);
        current.setStatus(status);
        return userRepo.save(current);
    }

    public String deleteUserById(int id) {
      Optional<User> userOpt=userRepo.findById(id);
      if(userOpt.isPresent()){
        userRepo.deleteById(id);
        return "User deleted successfully";
      }
      else{
        return "User not found";
      }
      
    }
        
}
