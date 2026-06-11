package com.journalapp.journalApp.controller;

import com.journalapp.journalApp.entity.JournalEntryEntity;
import com.journalapp.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping

    public ResponseEntity<List<JournalEntryEntity>> getAll() {
        return ResponseEntity.ok(
                journalEntryService.getAll());
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntryEntity>
    getById(@PathVariable String myId) {

        JournalEntryEntity entry =
                journalEntryService
                        .getById(new ObjectId(myId));

        if (entry != null) {
            return ResponseEntity.ok(entry);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createJournal(@RequestBody JournalEntryEntity myEntry) {

        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }


    }


    @DeleteMapping("/id/{myId}")
    public ResponseEntity<JournalEntryEntity> deleteById(
            @PathVariable String myId) {
        try {
            journalEntryService
                    .deleteEntryById(
                            new ObjectId(myId));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<JournalEntryEntity> update(
            @PathVariable String myId,
            @RequestBody JournalEntryEntity newEntry) {

        try {
            ObjectId objectId =
                    new ObjectId(myId);

            JournalEntryEntity old =
                    journalEntryService
                            .getUserById(
                                    objectId);

            if (old == null) {
                return new ResponseEntity<>(
                        HttpStatus.NOT_FOUND);
            }

            if (newEntry.getContent() != null
                    && !newEntry.getContent().isEmpty()) {

                old.setContent(
                        newEntry.getContent());
            }

            if (newEntry.getTitle() != null
                    && !newEntry.getTitle().isEmpty()) {

                old.setTitle(
                        newEntry.getTitle());
            }

            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}

