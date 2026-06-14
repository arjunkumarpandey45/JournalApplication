package com.journalapp.journalApp.controller;

import com.journalapp.journalApp.entity.JournalEntryEntity;
import com.journalapp.journalApp.entity.UserEntity;
import com.journalapp.journalApp.service.JournalEntryService;
import com.journalapp.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<List<JournalEntryEntity>> getAllJournalEntryOfUser(@PathVariable String userName) {
        UserEntity user = userService.findByUserName(userName);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<JournalEntryEntity> list = user.getJournalEntryEntities();
        return ResponseEntity.ok(list);
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

    @PostMapping("/{userName}")
    public ResponseEntity<?> createJournal(@RequestBody JournalEntryEntity myEntry, @PathVariable String userName) {

        try {
            myEntry.setDate(LocalDateTime.now());
           JournalEntryEntity saved= journalEntryService.saveEntry(myEntry, userName);
         ;
            return new ResponseEntity<>(saved, HttpStatus.CREATED);


        } catch (Exception e) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }


    }


    @DeleteMapping("/id/{username}/{myId}")
    public ResponseEntity<Void> deleteById(
            @PathVariable String myId,@PathVariable String username) {
        try {
            journalEntryService.deleteEntryById(new ObjectId(myId),username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/id/{userName}/{myId}")
    public ResponseEntity<JournalEntryEntity> update(
          @PathVariable String userName,
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

