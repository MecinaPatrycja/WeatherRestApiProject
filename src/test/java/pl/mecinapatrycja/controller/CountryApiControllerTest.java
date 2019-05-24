package pl.mecinapatrycja.controller;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.mecinapatrycja.exception.InvalidCountryCodeException;
import pl.mecinapatrycja.service.CountryApiService;
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CountryApiControllerTest {
    @Autowired
    private CountryApiController countryApiController;
    @Autowired
    private CountryApiService countryApiService;

    @Test
    public void okCountryName() {
        Assert.assertEquals(("Poland"), countryApiController.getJson("PL").getCountryName());
    }

    @Test(expected = InvalidCountryCodeException.class)
    public void invalidCountryCodeException() {
        Mockito.when(countryApiService.getAllCitiesFromSpecificCountry("codeDoesNotExist"))
                .thenThrow(InvalidCountryCodeException.class);
        countryApiController.getJson("codeDoesNotExist");
    }
}