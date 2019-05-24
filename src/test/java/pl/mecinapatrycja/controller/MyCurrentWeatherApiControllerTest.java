package pl.mecinapatrycja.controller;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.WebRequest;
import pl.mecinapatrycja.exception.InvalidCityNameException;
import pl.mecinapatrycja.exception.InvalidCountryCodeException;
import pl.mecinapatrycja.model.WeatherApiCurrentWeatherModel;
import pl.mecinapatrycja.service.MyCurrentWeatherApiService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MyCurrentWeatherApiControllerTest {
    @Autowired
    private MyCurrentWeatherApiController myCurrentWeatherApiController;
    @Autowired
    private MyCurrentWeatherApiService myCurrentWeatherApiService;
    @Mock
    private WebRequest webRequest;

    @Before
    public void setup() {
        Map<String, String[]> mockParameterMap = new HashMap<>();
        mockParameterMap.put("city", new String[]{"city1234", "Warsaw"});
        Mockito.when(webRequest.getParameterMap())
                .thenReturn(mockParameterMap);
    }

    @Test
    public void notNullResponseTwoCities() {
        ResponseEntity<List<WeatherApiCurrentWeatherModel>> mockResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(myCurrentWeatherApiService.currentWeatherInTwoCities("Warsaw", "London"))
                .thenReturn(mockResponse);
        ResponseEntity response = myCurrentWeatherApiService.currentWeatherInTwoCities("Warsaw", "London");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test(expected = InvalidCityNameException.class)
    public void invalidCityNameException() {
        Mockito.when(myCurrentWeatherApiService.currentWeatherInTwoCities("city1234", "Warsaw"))
                .thenThrow(InvalidCityNameException.class);
        myCurrentWeatherApiController.currentWeatherInTwoCities(webRequest);
    }

    @Test
    public void notNullResponseCountry() {
        ResponseEntity<List<WeatherApiCurrentWeatherModel>> mockResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(myCurrentWeatherApiService.currentWeatherInSpecificCountry("FR"))
                .thenReturn(mockResponse);
        ResponseEntity response = myCurrentWeatherApiService.currentWeatherInSpecificCountry("FR");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test(expected = InvalidCountryCodeException.class)
    public void invalidCountryCodeException() {
        Mockito.when(myCurrentWeatherApiService.currentWeatherInSpecificCountry("ABC"))
                .thenThrow(InvalidCountryCodeException.class);
        myCurrentWeatherApiController.currentWeatherInSpecificCountry("ABC");
    }
}