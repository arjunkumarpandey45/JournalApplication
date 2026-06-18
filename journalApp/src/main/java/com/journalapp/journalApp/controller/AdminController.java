package com.journalapp.journalApp.controller;

import com.journalapp.journalApp.Cache.AppCache;
import com.journalapp.journalApp.entity.UserEntity;
import com.journalapp.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
@Autowired
private UserService userService;
@Autowired
private AppCache appCache;
    @GetMapping("/all-users")
    public List<UserEntity> getAllUser(){
   try {
       List<UserEntity> userEntityList=   userService.getAllUser();
       return ResponseEntity.ok(userEntityList).getBody();
   } catch (Exception e) {
       throw new RuntimeException(e);
   }
    }

    @PostMapping("/create-admin")
    public UserEntity createAdmin(@RequestBody UserEntity userEntity){
        try {
            UserEntity userEntityList=   userService.saveAdmin(userEntity);
            return ResponseEntity.ok(userEntityList).getBody();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @GetMapping("/clear-cache")
    public void clearCache(){
appCache.init();
        System.out.println("CACHE AFTER RELOAD");
        System.out.println(appCache.APP_CACHE);
    }
}
