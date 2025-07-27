package net.Myapp.journalApp.service;

import net.Myapp.journalApp.apiresponse.WeatherResponse;
import net.Myapp.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

@Value("${weather.api.key}")
    private  String apikey;


    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city){
        if (appCache == null || appCache.appCache == null) {
            throw new RuntimeException("AppCache is not initialized.");
        }
        String urlTemplate = appCache.appCache.get("WEATHER_API");
        if (urlTemplate == null) {
            throw new RuntimeException("WEATHER_API not found in AppCache.");
        }
        else{
        String finalAPI=appCache.appCache.get("WEATHER_API").replace("<city>",city).replace("<apikey>",apikey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
      return response.getBody();

        }
    }

}
