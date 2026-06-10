package com.journalapp.journalApp.controller;

import com.journalapp.journalApp.entity.JournalEntryEntity;
import com.journalapp.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    public JournalEntryService journalEntryService;

    @GetMapping

    public List<JournalEntryEntity> getAll() {
        return journalEntryService.getAll();
    }

    @PostMapping
    public boolean createJournal(@RequestBody JournalEntryEntity myEntry) {
        myEntry.setDate(LocalDateTime.now());
        System.out.println(myEntry.getTitle());
        System.out.println(myEntry.getContent());

        journalEntryService.saveEntry(myEntry);
        return true;
    }



    @DeleteMapping("/id/{myId}")
    public boolean deleteById(
            @PathVariable String myId) {

        journalEntryService
                .deleteEntryById(
                        new ObjectId(myId));

        return true;
    }

    @PutMapping("/id/{myId}")
    public JournalEntryEntity update(
            @PathVariable String myId,
            @RequestBody JournalEntryEntity newEntry) {
        ObjectId objectId =
                new ObjectId(myId);

        JournalEntryEntity old =
                journalEntryService
                        .getUserById(
                               objectId);

        if (old == null) {
            System.out.println("ID NOT FOUND");
            return null;
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

        return old;
    }
}

