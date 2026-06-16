package com.journalapp.journalApp.service;

import com.journalapp.journalApp.entity.UserEntity;
import com.journalapp.journalApp.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepo userRepo;

//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserEntity saveNewUser(UserEntity userEntity) {
        try {
            userEntity.setDate(LocalDateTime.now());
            userEntity.setRoles(List.of("USER"));
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            return userRepo.save(userEntity);
        } catch (Exception e) {
//            logger.info("Problem Occurred For {} : ",userEntity.getUserName(),e);
//            logger.warn("hahaha");
//            logger.error("hahaha");
//            logger.debug("hahaha");
//            logger.trace("hahaha");
            log.warn("hahaha");
            log.error("hahaha");
            log.debug("hahaha");
            log.trace("hahaha");
            log.info("Problem Occurred For {} : ",userEntity.getUserName(),e);
            throw new RuntimeException(e);
        }

    }

    public void saveUser(UserEntity userEntity) {
        userEntity.setDate(LocalDateTime.now());

        userRepo.save(userEntity);

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

    public UserEntity findByUserName(String userName) {
        return userRepo.findByUserName(userName);
    }

    public UserEntity saveAdmin(UserEntity userEntity) {
        userEntity.setDate(LocalDateTime.now());
        userEntity.setRoles(List.of("USER", "ADMIN"));
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepo.save(userEntity);

    }
}
