package pl.mecinapatrycja.service.impl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.mecinapatrycja.constant.DayEnum;
import pl.mecinapatrycja.exception.InvalidDateException;
import pl.mecinapatrycja.model.ForecastApi;
import pl.mecinapatrycja.model.ForecastApiDay;
import pl.mecinapatrycja.model.ForecastApiModel;
import pl.mecinapatrycja.service.MyForecastApiService;
import pl.mecinapatrycja.validator.CityNameValidator;
import pl.mecinapatrycja.validator.DateValidator;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class MyForecastApiServiceImpl implements MyForecastApiService {
    private final ForecastApiServiceImpl forecastApiService;

    @Override
    public ResponseEntity<ForecastApi> forecastForTheDate(String city, String date) {
        CityNameValidator.validate(city);
        DateValidator.validate(date);
        ForecastApiModel forecast = forecastApiService.getJson(city, DayEnum.SEVEN.getDay()).getBody();
        List<ForecastApiDay> forecastApiDayList = forecast.getForecast().getForecastDay();
        List<ForecastApiDay> result = new ArrayList<>();
        for (ForecastApiDay forecastApiDay : forecastApiDayList) {
            if (forecastApiDay.getDate().equals(date)) {
                result.add(forecastApiDay);
            }
        }
        forecast.getForecast().setForecastDay(result);
        if (result.isEmpty()) {
            throw new InvalidDateException();
        }
        return new ResponseEntity<>(forecast.getForecast(), HttpStatus.OK);
    }
}
