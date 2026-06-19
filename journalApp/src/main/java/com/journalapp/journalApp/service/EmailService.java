package com.journalapp.journalApp.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendMail(String to,String subject ,String body) {
     try {
         SimpleMailMessage simpleMailMessage =new SimpleMailMessage();
         simpleMailMessage.setTo(to);
         simpleMailMessage.setSubject(subject);
         simpleMailMessage.setText(body);
         javaMailSender.send(simpleMailMessage);
     } catch (Exception e) {
        log.error("Error While Sending mail",e);
     }

    }
}