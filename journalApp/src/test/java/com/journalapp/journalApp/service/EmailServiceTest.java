package com.journalapp.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;
    @Test
    public  void sendMailTest(){
        emailService.sendMail("arjun7236201798@gmail.com","From JournalApp","Hi,How are You ?");
    }
}
