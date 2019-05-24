package pl.mecinapatrycja.service.impl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.mecinapatrycja.model.CountryApiModel;
import pl.mecinapatrycja.model.WeatherApiCurrentWeatherModel;
import pl.mecinapatrycja.service.MyCurrentWeatherApiService;
import pl.mecinapatrycja.validator.CityNameValidator;
import pl.mecinapatrycja.validator.CountryCodeValidator;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class MyCurrentWeatherApiServiceImpl implements MyCurrentWeatherApiService {
    private final CurrentWeatherApiServiceImpl apixuCurrentWeatherService;
    private final CountryApiServiceImpl countryApiService;

    @Override
    public ResponseEntity<List<WeatherApiCurrentWeatherModel>> currentWeatherInTwoCities(String city1, String city2) {
        CityNameValidator.validate(city1);
        CityNameValidator.validate(city2);
        List<String> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
        ResponseEntity<List<WeatherApiCurrentWeatherModel>> responseEntity =
                apixuCurrentWeatherService.getJsonForManyCities(cities);
        return responseEntity;
    }

    @Override
    public ResponseEntity<List<WeatherApiCurrentWeatherModel>> currentWeatherInSpecificCountry(String country) {
        CountryCodeValidator.validate(country);
        CountryApiModel countryApiModel = countryApiService.getAllCitiesFromSpecificCountry(country);
        ResponseEntity<List<WeatherApiCurrentWeatherModel>> responseEntity =
                apixuCurrentWeatherService.getJsonForManyCities(countryApiModel.getCities());
        List<WeatherApiCurrentWeatherModel> body = responseEntity.getBody();
        List<WeatherApiCurrentWeatherModel> okList = new ArrayList<>();
        for (WeatherApiCurrentWeatherModel weatherApiCurrentWeatherModel : body) {
            if (weatherApiCurrentWeatherModel.getLocationModel().getCountry().equals(countryApiModel.getCountryName())) {
                okList.add(weatherApiCurrentWeatherModel);
            }
        }
        return new ResponseEntity<>(okList, HttpStatus.OK);
    }
}
