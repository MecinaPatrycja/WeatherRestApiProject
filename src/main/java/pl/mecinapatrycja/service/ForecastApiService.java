package pl.mecinapatrycja.service;
import org.springframework.http.ResponseEntity;
import pl.mecinapatrycja.model.ForecastApiModel;
public interface ForecastApiService {
    ResponseEntity<ForecastApiModel> getJson(String city, String days);

}
