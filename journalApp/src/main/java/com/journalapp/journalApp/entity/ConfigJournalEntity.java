package com.journalapp.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("journal_app_config")
@Data
@NoArgsConstructor
public class ConfigJournalEntity {

    private String key;
    private String value;

}
