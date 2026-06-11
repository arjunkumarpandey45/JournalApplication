package com.journalapp.journalApp.repository;

import com.journalapp.journalApp.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserEntity, ObjectId> {
    UserEntity findByUserName(String userName);
}

