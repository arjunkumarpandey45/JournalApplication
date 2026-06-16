package com.journalapp.journalApp.service;

import com.journalapp.journalApp.entity.UserEntity;
import com.journalapp.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class UserDetailServiceImplTest {

@InjectMocks
    private UserDetailServiceImpl userDetailService;

    @Mock
    private UserRepo userRepo;
@BeforeEach
void setup(){
    MockitoAnnotations.openMocks(this);
}
    @Test
    void loadByUserNameTest() {

        UserEntity user =
                new UserEntity(
                        "ArjunPadey",
                        "12353"
                );

        user.setRoles(
                List.of("USER"));
        when(userRepo.findByUserName(anyString()))
                .thenReturn(user);

        UserDetails userDetails =
                userDetailService
                        .loadUserByUsername("ArjunPadey");

        Assertions.assertNotNull(userDetails);
    }
}