package com.journalapp.journalApp.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {
    @Value("${MONGO_URI_A}")
    private String mongoUriA;

    @Value("${MONGO_URI_B}")
    private String mongoUriB;

    @Value("${MONGO_URI_C}")
    private String mongoUriC;
    @Bean
    public MongoClient mongoClient() {

        String[] uris = {
                mongoUriA,mongoUriB,mongoUriC




        };

        MongoClient fallbackClient = null;

        for (String uri : uris) {
            try {

                MongoClient client = MongoClients.create(uri);

                Document result = client.getDatabase("admin")
                        .runCommand(new Document("hello", 1));

                boolean isPrimary = result.getBoolean("isWritablePrimary", false);

                if (isPrimary) {

                    return client;
                }



                // fallback save karlo agar primary na mile
                if (fallbackClient == null) {
                    fallbackClient = client;
                }

            } catch (Exception e) {

            }
        }

        // agar primary na mile to secondary use kar lo
        if (fallbackClient != null) {
            System.out.println("Using Secondary as fallback...");
            return fallbackClient;
        }

        throw new RuntimeException("No MongoDB server available");
    }
}