package net.Myapp.journalApp.service;

import net.Myapp.journalApp.apiresponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {


    private static final String APIKEY="ec6e6b6260546acbe9a5c312084f1860";
    private static final String API= "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";


    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalAPI=API.replace("CITY",city).replace("API_KEY",APIKEY);
        System.out.println(finalAPI);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
      return response.getBody();
    }
}
