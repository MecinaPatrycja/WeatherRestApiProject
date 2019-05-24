package pl.mecinapatrycja.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mecinapatrycja.model.WeatherApiCurrentWeatherModel;
import pl.mecinapatrycja.service.impl.CurrentWeatherApiServiceImpl;
import pl.mecinapatrycja.validator.CityNameValidator;
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/weather")
public class CurrentWeatherApiController {
    private final CurrentWeatherApiServiceImpl apixuCurrentWeatherService;

    //EXAMPLE REQUEST: http://localhost:8080/weather?city=Warsaw
    @GetMapping
    public ResponseEntity<WeatherApiCurrentWeatherModel> getJson(String city) {
        CityNameValidator.validate(city);
        return apixuCurrentWeatherService.getJson(city);
    }
}
