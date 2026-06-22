package com.journalapp.journalApp.service;

import com.journalapp.journalApp.Scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserScheduler userScheduler;
    @Test
    public  void fetchUserAndSentAnalysisMailTest(){
        userScheduler.fetchUserAndSentAnalysisMail();
    }
}
