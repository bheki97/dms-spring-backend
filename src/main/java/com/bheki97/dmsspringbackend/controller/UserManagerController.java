package com.bheki97.dmsspringbackend.controller;

import com.bheki97.dmsspringbackend.entity.UserEntity;
import com.bheki97.dmsspringbackend.service.userentitymanager.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserManagerController {

    @Autowired
    private UserEntityManager userManager;

    @PostMapping
    public UserEntity addUser(@RequestBody UserEntity user){
        return userManager.addNewUser(user);
    }

    @PutMapping
    public UserEntity updateUser(@RequestBody UserEntity entity){
        return userManager.updateUser(entity);
    }


    @GetMapping
    public List<UserEntity>getAllUsers(){
        return  userManager.getAllUsers();
    }

    @GetMapping("/{email}")
    public UserEntity getUserByEmail(@PathVariable String email){
        System.out.println(email);
        return userManager.getUserByEmail(email);
    }




}
