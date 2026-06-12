package com.journalapp.journalApp.service;

import com.journalapp.journalApp.entity.UserEntity;
import com.journalapp.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;


    public UserEntity saveUser(UserEntity userEntity) {
        userEntity.setDate(LocalDateTime.now());

           return userRepo.save(userEntity);

    }

    public List<UserEntity> getAllUser() {
        return userRepo.findAll();
    }

    public UserEntity getUserById(ObjectId id) {
        return userRepo.findById(id).orElse(null);
    }

    public void deleteUserById(ObjectId id) {
        userRepo.deleteById(id);
    }
    public UserEntity findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }
}
