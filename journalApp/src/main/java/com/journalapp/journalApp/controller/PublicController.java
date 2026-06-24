package com.journalapp.journalApp.controller;

import com.journalapp.journalApp.Utils.JwtUtils;
import com.journalapp.journalApp.entity.UserEntity;
import com.journalapp.journalApp.service.UserDetailServiceImpl;
import com.journalapp.journalApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signup(@RequestBody UserEntity userEntity) {
        UserEntity saved = userService.saveNewUser(userEntity);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity userEntity) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEntity.getUserName(), userEntity.getPassword()));
            UserDetails userDetails =
                    userDetailService.loadUserByUsername(userEntity.getUserName());
            String jwt =jwtUtils.generateToken(userEntity.getUserName());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (AuthenticationException e) {
            log.error("Exception occurred while createAuthenticationToken ", e);
            throw new RuntimeException(e);
        }

    }
}
