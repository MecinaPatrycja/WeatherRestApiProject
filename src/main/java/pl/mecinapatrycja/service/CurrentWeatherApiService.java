package pl.mecinapatrycja.service;
import org.springframework.http.ResponseEntity;
import pl.mecinapatrycja.model.WeatherApiCurrentWeatherModel;
public interface CurrentWeatherApiService {
    ResponseEntity<WeatherApiCurrentWeatherModel> getJson(String city);
}
