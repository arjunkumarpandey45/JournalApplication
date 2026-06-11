package com.journalapp.journalApp.service;

import com.journalapp.journalApp.repository.JournalEntryRepo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.journalapp.journalApp.entity.JournalEntryEntity;

import java.util.List;

@Service
public class JournalEntryService {
    @Autowired
    public JournalEntryRepo journalEntryRepo;

    public void saveEntry(JournalEntryEntity journalEntryEntity) {
        JournalEntryEntity saved = journalEntryRepo.save(journalEntryEntity);

        System.out.println(saved);
    }
    public List<JournalEntryEntity> getAll() {
        return journalEntryRepo.findAll();
    }

    public void deleteEntryById(ObjectId id) {
        journalEntryRepo.deleteById(id);


    }
    public JournalEntryEntity getUserById(ObjectId id){
       return journalEntryRepo.findById(id).orElse(null);


    }

    public JournalEntryEntity getById(ObjectId id) {
      return   journalEntryRepo.findById(id).orElse(null);
    }
}
