package com.journalapp.journalApp.Cache;

import com.journalapp.journalApp.entity.ConfigJournalEntity;
import com.journalapp.journalApp.repository.ConfigRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    @Autowired
    private ConfigRepo configRepo;
    public Map<String, String> APP_CACHE ;

    @PostConstruct
    public void init() {
        APP_CACHE =new HashMap<>();
        List<ConfigJournalEntity> all = configRepo.findAll();
        System.out.println(all);
        for (ConfigJournalEntity configJournalEntity : all) {
            System.out.println(
                    "KEY = " + configJournalEntity.getKey()
                            + " VALUE = " + configJournalEntity.getValue()
            );
            APP_CACHE.put(configJournalEntity.getKey(), configJournalEntity.getValue());
        }
        System.out.println(APP_CACHE);
    }
}
