package pl.mecinapatrycja.service;
import org.springframework.http.ResponseEntity;
import pl.mecinapatrycja.model.WeatherApiCurrentWeatherModel;

import java.util.List;
public interface MyCurrentWeatherApiService {
    ResponseEntity<List<WeatherApiCurrentWeatherModel>> currentWeatherInTwoCities(String city1, String city2);
    ResponseEntity<List<WeatherApiCurrentWeatherModel>> currentWeatherInSpecificCountry(String country);
}
