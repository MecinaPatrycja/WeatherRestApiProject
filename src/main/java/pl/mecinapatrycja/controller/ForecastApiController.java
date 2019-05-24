package pl.mecinapatrycja.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mecinapatrycja.model.ForecastApiModel;
import pl.mecinapatrycja.service.impl.ForecastApiServiceImpl;
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/forecast")
public class ForecastApiController {
    private final ForecastApiServiceImpl forecastApiService;

    //EXAMPLE REQUEST: http://localhost:8080/forecast?city=Poznan&days=4
    @GetMapping
    public ResponseEntity<ForecastApiModel> getJson(String city, String days) {
        return forecastApiService.getJson(city, days);
    }
}
