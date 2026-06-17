package com.journalapp.journalApp.service;

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
    public  String apiKey;

    public static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;
    public WeatherResponse getWeather(String CITY) {
        System.out.println(apiKey);
        String finalAPI = API.replace("API_KEY", apiKey).replace("CITY", CITY);
     ResponseEntity<WeatherResponse> response=   restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
WeatherResponse data=response.getBody();
        return data ;
    }
}
