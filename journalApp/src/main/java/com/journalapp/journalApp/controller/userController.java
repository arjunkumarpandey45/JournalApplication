package com.journalapp.journalApp.controller;

import com.journalapp.journalApp.entity.UserEntity;
import com.journalapp.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUser() {
        List<UserEntity> userList = userService.getAllUser();
        if (userList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userList);
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        UserEntity saved = userService.saveUser(userEntity);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(
            @RequestBody UserEntity user,
            @PathVariable String userName) {

        UserEntity userInDb = userService.findByUserName(userName);

        // User not found
        if (userInDb == null) {
            return ResponseEntity.notFound().build();
        }

        // Update fields
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());

        // Save updated user
        UserEntity updatedUser = userService.saveUser(userInDb);

        // Return updated user
        return ResponseEntity.ok(updatedUser);
    }
}
