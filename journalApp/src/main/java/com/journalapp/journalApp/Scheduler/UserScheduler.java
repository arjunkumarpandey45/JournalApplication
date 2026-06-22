package com.journalapp.journalApp.Scheduler;

import com.journalapp.journalApp.Cache.AppCache;
import com.journalapp.journalApp.Enums.Sentiment;

import com.journalapp.journalApp.entity.JournalEntryEntity;
import com.journalapp.journalApp.entity.UserEntity;
import com.journalapp.journalApp.repository.UserRepoImpl;
import com.journalapp.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserScheduler {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepoImpl userRepoImpl;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 0 * * Sat,Sun ")
//    @Scheduled(cron = "0 * * * * * ")
    public void fetchUserAndSentAnalysisMail() {
        List<UserEntity> userlist = userRepoImpl.getUserForSA();
        for (UserEntity user : userlist) {
            List<JournalEntryEntity> journalEntryEntities = user.getJournalEntryEntities();
            List<Sentiment> sentiments = journalEntryEntities.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).toList();
            Map<Sentiment, Integer> sentimentCount = new HashMap<>();

            for (Sentiment sentiment : sentiments) {
                if (sentiment != null) {
                    sentimentCount.put(
                            sentiment,
                            sentimentCount.getOrDefault(sentiment, 0) + 1
                    );
                }
            }

            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;

            for (Map.Entry<Sentiment, Integer> entry : sentimentCount.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            if (mostFrequentSentiment != null) {
                emailService.sendMail(
                        user.getEmail(),
                        "Sentiment Analysis for Last 7 Days",
                        "Your dominant sentiment was: " + mostFrequentSentiment
                );
            }

        }

    }

    @Scheduled(cron = "0 0 0 * * Sat,Sun ")
    public void refreshCache() {
        appCache.init();
    }
}
