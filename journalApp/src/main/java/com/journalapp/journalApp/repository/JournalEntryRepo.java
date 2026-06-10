package com.journalapp.journalApp.repository;

import com.journalapp.journalApp.entity.JournalEntryEntity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryRepo extends MongoRepository<JournalEntryEntity, ObjectId> {

}
