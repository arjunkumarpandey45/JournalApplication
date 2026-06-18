package com.journalapp.journalApp.repository;

import com.journalapp.journalApp.entity.ConfigJournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigRepo extends MongoRepository<ConfigJournalEntity, ObjectId> {
}
