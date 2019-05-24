package pl.mecinapatrycja.service;
import org.springframework.http.ResponseEntity;
import pl.mecinapatrycja.model.ForecastApi;
public interface MyForecastApiService {
    ResponseEntity<ForecastApi> forecastForTheDate(String city, String date);
}
