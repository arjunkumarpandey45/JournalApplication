package com.journalapp.journalApp.service;

import com.journalapp.journalApp.Cache.AppCache;
import com.journalapp.journalApp.apiResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    @Value("${WEATHER_API_KEY}")
    public String apiKey;
    @Autowired
    private AppCache appCache;


    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        System.out.println(apiKey);
        String finalAPI = appCache.APP_CACHE.get("weather_api").replace("<apiKey>", apiKey).replace("<city>", city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse data = response.getBody();
        return data;
    }
}
