package pl.mecinapatrycja.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import pl.mecinapatrycja.model.WeatherApiCurrentWeatherModel;
import pl.mecinapatrycja.service.impl.MyCurrentWeatherApiServiceImpl;

import java.util.List;
import java.util.Map;
@RestController
@RequiredArgsConstructor
@RequestMapping("/current-weather")
public class MyCurrentWeatherApiController {
    private final MyCurrentWeatherApiServiceImpl myWeatherService;

    //EXAMPLE REQUEST: http://localhost:8080/current-weather/two-cities?city=Lodz&city=Berlin
    @GetMapping(value = "/two-cities")
    public ResponseEntity<List<WeatherApiCurrentWeatherModel>> currentWeatherInTwoCities(WebRequest webRequest) {
        Map<String, String[]> parameterMap = webRequest.getParameterMap();
        String[] cities = parameterMap.get("city");
        String city1 = cities[0];
        String city2 = cities[1];
        return myWeatherService.currentWeatherInTwoCities(city1, city2);
    }

    //EXAMPLE REQUEST: http://localhost:8080/current-weather/country?code=GB
    @GetMapping(value = "/country")
    public ResponseEntity<List<WeatherApiCurrentWeatherModel>> currentWeatherInSpecificCountry(String code) {
        return myWeatherService.currentWeatherInSpecificCountry(code);
    }
}
