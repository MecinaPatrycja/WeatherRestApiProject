package pl.mecinapatrycja.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mecinapatrycja.model.ForecastApi;
import pl.mecinapatrycja.service.impl.MyForecastApiServiceImpl;
@RestController
@RequiredArgsConstructor
@RequestMapping("/forecast-for-date")
public class MyForecastApiController {
    private final MyForecastApiServiceImpl myForecastApiService;

    //EXAMPLE REQUEST: http://localhost:8080/forecast-for-date?city=BuenosAires&date=2019-05-03
    @GetMapping
    public ResponseEntity<ForecastApi> forecastForTheDate(String city, String date) {
        return myForecastApiService.forecastForTheDate(city, date);
    }
}
