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
import pl.mecinapatrycja.exception.InvalidDayException;
import pl.mecinapatrycja.model.ForecastApiModel;
import pl.mecinapatrycja.service.ForecastApiService;
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ForecastApiControllerTest {
    @Autowired
    private ForecastApiController forecastApiController;
    @Autowired
    private ForecastApiService forecastApiService;

    @Test
    public void notNullResponse() {
        ResponseEntity<ForecastApiModel> mockResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(forecastApiService.getJson("Warsaw", "5"))
                .thenReturn(mockResponse);
        ResponseEntity response = forecastApiController.getJson("Warsaw", "5");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test(expected = InvalidCityNameException.class)
    public void invalidCityNameException() {
        Mockito.when(forecastApiService.getJson("city1234", "4"))
                .thenThrow(InvalidCityNameException.class);
        forecastApiController.getJson("city1234", "4");
    }

    @Test(expected = InvalidDayException.class)
    public void invalidDateException() {
        Mockito.when(forecastApiService.getJson("Warsaw", "itIsNotNumber"))
                .thenThrow(InvalidDayException.class);
        forecastApiController.getJson("Warsaw", "itIsNotNumber");
    }
}