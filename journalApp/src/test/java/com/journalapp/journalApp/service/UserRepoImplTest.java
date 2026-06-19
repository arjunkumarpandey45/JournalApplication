package com.journalapp.journalApp.service;

import com.journalapp.journalApp.repository.UserRepoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepoImplTest {
    @Autowired
    private UserRepoImpl userRepoImpl;
    @Test
    void testNewUsers(){
        userRepoImpl.getUserForSA();
    }
}
