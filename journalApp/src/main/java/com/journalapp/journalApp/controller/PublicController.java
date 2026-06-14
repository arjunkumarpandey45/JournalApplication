package com.journalapp.journalApp.controller;

import com.journalapp.journalApp.entity.UserEntity;
import com.journalapp.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;


    @PostMapping("/create-user")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        UserEntity saved = userService.saveNewUser(userEntity);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
