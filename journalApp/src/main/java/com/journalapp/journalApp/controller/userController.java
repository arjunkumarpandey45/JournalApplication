package com.journalapp.journalApp.controller;

import com.journalapp.journalApp.apiResponse.WeatherResponse;
import com.journalapp.journalApp.entity.UserEntity;
import com.journalapp.journalApp.repository.UserRepo;
import com.journalapp.journalApp.service.UserService;
import com.journalapp.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
@Autowired
private WeatherService weatherService;
    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntity userInDb = userService.findByUserName(userName);

        // User not found
        if (userInDb == null) {
            return ResponseEntity.notFound().build();
        }

        // Update fields
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());

        // Save updated user
        UserEntity updatedUser = userService.saveNewUser(userInDb);

        // Return updated user
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteUserByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>("Hi "  +authentication.getName()+ " ,Weather feels like "+ weatherService.getWeather("Lucknow").getCurrent().getFeelslike(),HttpStatus.OK);
    }
}
