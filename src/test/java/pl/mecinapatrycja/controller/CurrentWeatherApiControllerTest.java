package pl.mecinapatrycja.controller;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.mecinapatrycja.exception.InvalidCityNameException;
import pl.mecinapatrycja.model.WeatherApiCurrentWeatherModel;
import pl.mecinapatrycja.service.CurrentWeatherApiService;
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CurrentWeatherApiControllerTest {
    @Autowired
    private CurrentWeatherApiController currentWeatherApiController;
    @Autowired
    private CurrentWeatherApiService currentWeatherApiService;

    @Test
    public void notNullResponse() {
        ResponseEntity<WeatherApiCurrentWeatherModel> mockResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(currentWeatherApiService.getJson("Warsaw"))
                .thenReturn(mockResponse);
        ResponseEntity response = currentWeatherApiController.getJson("Warsaw");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test(expected = InvalidCityNameException.class)
    public void invalidCityNameException() {
        Mockito.when(currentWeatherApiService.getJson("city1234"))
                .thenThrow(InvalidCityNameException.class);
        currentWeatherApiController.getJson("city1234");
    }
}