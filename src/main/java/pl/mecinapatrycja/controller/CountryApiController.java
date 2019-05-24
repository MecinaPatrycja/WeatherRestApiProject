package pl.mecinapatrycja.controller;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mecinapatrycja.model.CountryApiModel;
import pl.mecinapatrycja.service.impl.CountryApiServiceImpl;
@RestController
@RequiredArgsConstructor
@RequestMapping("/country")
@ApiModel
public class CountryApiController {
    private final CountryApiServiceImpl countryApiService;

    // EXAMPLE REQUEST: http://localhost:8080/country?code=PL
    @GetMapping
    public CountryApiModel getJson(@ApiParam(defaultValue = "PL", name = "code", value = "GB") String code) {
        return countryApiService.getAllCitiesFromSpecificCountry(code);
    }
}
