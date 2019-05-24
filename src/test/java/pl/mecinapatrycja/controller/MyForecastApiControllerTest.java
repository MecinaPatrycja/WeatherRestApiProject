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
import pl.mecinapatrycja.exception.InvalidDateException;
import pl.mecinapatrycja.model.ForecastApi;
import pl.mecinapatrycja.service.MyForecastApiService;

import java.time.LocalDate;
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MyForecastApiControllerTest {
    @Autowired
    private MyForecastApiController myForecastApiController;
    @Autowired
    private MyForecastApiService myForecastApiService;

    @Test
    public void notNullResponse() {
        String localDate = LocalDate.now().plusDays(5).toString();
        ResponseEntity<ForecastApi> mockResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(myForecastApiService.forecastForTheDate("Warsaw", localDate))
                .thenReturn(mockResponse);
        ResponseEntity response = myForecastApiController.forecastForTheDate("Warsaw", localDate);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test(expected = InvalidCityNameException.class)
    public void invalidCityNameException() {
        String localDate = LocalDate.now().plusDays(5).toString();
        Mockito.when(myForecastApiService.forecastForTheDate("city1234", localDate))
                .thenThrow(InvalidCityNameException.class);
        myForecastApiController.forecastForTheDate("city1234", "4");
    }

    @Test(expected = InvalidDateException.class)
    public void invalidDateException() {
        Mockito.when(myForecastApiService.forecastForTheDate("Warsaw", "wrongDate"))
                .thenThrow(InvalidDateException.class);
        myForecastApiController.forecastForTheDate("Warsaw", "wrongDate");
    }
}