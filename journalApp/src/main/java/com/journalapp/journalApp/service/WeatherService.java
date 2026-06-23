package com.journalapp.journalApp.service;

import com.journalapp.journalApp.Cache.AppCache;
import com.journalapp.journalApp.apiResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {
        System.out.println(apiKey);

        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        System.out.println("CACHE = " + weatherResponse);
        if(weatherResponse!= null){
            System.out.println("CACHE HIT");
            return weatherResponse;
        }
        if(weatherResponse!=null){
            return weatherResponse;
        }else{
            String finalAPI = appCache.APP_CACHE.get("weather_api").replace("<apiKey>", apiKey).replace("<city>", city);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse data = response.getBody();
            if (data!=null){
                redisService.set("weather_of_" + city,data, 300L);
            }
            return data;
        }


    }
}

