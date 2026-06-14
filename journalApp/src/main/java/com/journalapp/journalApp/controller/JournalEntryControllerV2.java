package com.journalapp.journalApp.controller;

import com.journalapp.journalApp.entity.JournalEntryEntity;
import com.journalapp.journalApp.entity.UserEntity;
import com.journalapp.journalApp.service.JournalEntryService;
import com.journalapp.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> getAllJournalEntryOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntity user = userService.findByUserName(userName);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<JournalEntryEntity> list = user.getJournalEntryEntities();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntryEntity> getById(
            @PathVariable String myId) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String userName = authentication.getName();

        UserEntity userEntity =
                userService.findByUserName(userName);

        boolean exists = userEntity
                .getJournalEntryEntities()
                .stream()
                .anyMatch(entry ->
                        entry.getId().toString().equals(myId));

        if (!exists) {
            return ResponseEntity.notFound().build();
        }

        JournalEntryEntity journalEntry =
                journalEntryService
                        .getById(new ObjectId(myId));

        return journalEntry != null
                ? ResponseEntity.ok(journalEntry)
                : ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<?> createJournal(@RequestBody JournalEntryEntity myEntry) {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        String userName = authentication.getName();
        try {
            myEntry.setDate(LocalDateTime.now());
            JournalEntryEntity saved = journalEntryService.saveEntry(myEntry, userName);
            ;
            return new ResponseEntity<>(saved, HttpStatus.CREATED);


        } catch (Exception e) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }


    }



    @DeleteMapping("/id/{myId}")
    public ResponseEntity<Void> deleteById(
            @PathVariable String myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        boolean entry;
        try {
           entry=  journalEntryService.deleteEntryById(new ObjectId(myId), username);
          if(entry){
              return new ResponseEntity<>(HttpStatus.OK);
          }else {
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }


        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<JournalEntryEntity> update(

            @PathVariable String myId,
            @RequestBody JournalEntryEntity newEntry) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String userName = authentication.getName();

        UserEntity userEntity =
                userService.findByUserName(userName);

        boolean exists = userEntity
                .getJournalEntryEntities()
                .stream()
                .anyMatch(entry ->
                        entry.getId()
                                .toString()
                                .equals(myId));

        if (!exists) {
            return ResponseEntity.notFound().build();
        }

        try {

            JournalEntryEntity old =
                    journalEntryService
                            .getById(new ObjectId(myId));

            if (old == null) {
                return ResponseEntity.notFound().build();
            }

            if (newEntry.getTitle() != null
                    && !newEntry.getTitle().isBlank()) {

                old.setTitle(newEntry.getTitle());
            }

            if (newEntry.getContent() != null
                    && !newEntry.getContent().isBlank()) {

                old.setContent(newEntry.getContent());
            }

            journalEntryService.saveEntry(old);

            return ResponseEntity.ok(old);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().build();
        }
    }

}

