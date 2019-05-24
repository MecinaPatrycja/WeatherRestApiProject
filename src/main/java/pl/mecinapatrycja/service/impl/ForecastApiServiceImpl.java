package pl.mecinapatrycja.service.impl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.mecinapatrycja.exception.InvalidDayException;
import pl.mecinapatrycja.model.ForecastApiModel;
import pl.mecinapatrycja.service.ForecastApiService;
import pl.mecinapatrycja.validator.CityNameValidator;
import pl.mecinapatrycja.validator.DayValidator;

import java.net.URI;
import java.util.Arrays;
@Service
public class ForecastApiServiceImpl implements ForecastApiService {
    @Value("${APIXU_FORECAST_CORE_URL}")
    private String coreURL;
    @Value("${APIXU_API_KEY}")
    private String apiKey;

    public ResponseEntity<ForecastApiModel> getJson(String city, String days) {
        CityNameValidator.validate(city);
        DayValidator.validate(days);
        final int number = 7;
        if (Integer.parseInt(days) > number) {
            throw new InvalidDayException();
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>("parameters", httpHeaders);
        URI url = uriBuilder(city, days);
        ResponseEntity<ForecastApiModel> exchange =
                restTemplate.exchange(url, HttpMethod.GET, httpEntity, ForecastApiModel.class);
        return exchange;
    }

    private URI uriBuilder(String city, String days) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("key", Arrays.asList(apiKey));
        params.put("q", Arrays.asList(city));
        params.put("days", Arrays.asList(days));
        return UriComponentsBuilder.fromHttpUrl(coreURL).queryParams(params).encode().build().toUri();
    }
}
