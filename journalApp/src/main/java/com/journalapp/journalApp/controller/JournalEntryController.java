package com.journalapp.journalApp.controller;

import com.journalapp.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createJournal(@RequestBody JournalEntry myEntry) {
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getById(@PathVariable long myId) {
        return journalEntries.get(myId);
    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteById(@PathVariable long myId) {
        journalEntries.remove(myId);
        return true;
    }

    @PutMapping("/id/{myId}")
    public JournalEntry update(@PathVariable long myId, @RequestBody JournalEntry myEntry) {
        myEntry.setId(myId);
         journalEntries.put(myId, myEntry);
         return myEntry;
    }
}

