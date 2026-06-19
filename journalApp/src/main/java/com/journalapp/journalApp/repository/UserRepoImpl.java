package com.journalapp.journalApp.repository;

import com.journalapp.journalApp.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserRepoImpl {
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<UserEntity> getUserForSA(){
        Query query =new Query();
        query.addCriteria(Criteria.where("email")
                .regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
       List<UserEntity> userList= mongoTemplate.find(query,UserEntity.class);
       return userList;
    }
}
