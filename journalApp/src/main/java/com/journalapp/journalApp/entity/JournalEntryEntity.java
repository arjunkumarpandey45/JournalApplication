package com.journalapp.journalApp.entity;

import com.journalapp.journalApp.Enums.Sentiment;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Entries")
@Data
@NoArgsConstructor
//@Getter
//@Setter
public class JournalEntryEntity {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;



}
