package com.journalapp.journalApp.service;

import com.journalapp.journalApp.entity.UserEntity;
import com.journalapp.journalApp.repository.JournalEntryRepo;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.journalapp.journalApp.entity.JournalEntryEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserService userService;

    @Transactional
    public JournalEntryEntity saveEntry(JournalEntryEntity journalEntryEntity, String userName) {
        UserEntity user = userService.findByUserName(userName);
        JournalEntryEntity saved = journalEntryRepo.save(journalEntryEntity);

        user.getJournalEntryEntities().add(saved);
        userService.saveUser(user);
        return saved;
    }

    public void saveEntry(JournalEntryEntity journalEntryEntity) {

        journalEntryRepo.save(journalEntryEntity);
    }

    public List<JournalEntryEntity> getAll() {
        return journalEntryRepo.findAll();
    }

    @Transactional
    public boolean deleteEntryById(ObjectId id, String userName) {
        boolean removed=false;
        try {
            UserEntity user = userService.findByUserName(userName);
            removed = user.getJournalEntryEntities().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepo.deleteById(id);
            }else{
                ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            throw new RuntimeException("Khel KHTAM",e);
        }
        return removed;
    }

    public JournalEntryEntity getUserById(ObjectId id) {
        return journalEntryRepo.findById(id).orElse(null);


    }

    public JournalEntryEntity getById(ObjectId id) {
        return journalEntryRepo.findById(id).orElse(null);
    }

    public List<JournalEntryEntity> findByUserName(String userName) {
        return userService.findByUserName(userName).getJournalEntryEntities();
    }
}
