package pl.mecinapatrycja.service.impl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.mecinapatrycja.model.WeatherApiCurrentWeatherModel;
import pl.mecinapatrycja.service.CurrentWeatherApiService;
import pl.mecinapatrycja.validator.CityNameValidator;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class CurrentWeatherApiServiceImpl implements CurrentWeatherApiService {
    @Value("${APIXU_CORE_URL}")
    private String coreURL;
    @Value("${APIXU_API_KEY}")
    private String apiKey;

    public ResponseEntity<WeatherApiCurrentWeatherModel> getJson(String city) {
        CityNameValidator.validate(city);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>("parameters", httpHeaders);
        URI url = uriBuilder(city);
        ResponseEntity<WeatherApiCurrentWeatherModel> exchange =
                restTemplate.exchange(url, HttpMethod.GET, httpEntity, WeatherApiCurrentWeatherModel.class);
        return exchange;
    }

    public ResponseEntity<List<WeatherApiCurrentWeatherModel>> getJsonForManyCities(List<String> cities) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>("parameters", httpHeaders);
        List<WeatherApiCurrentWeatherModel> toReturn = new ArrayList<>();
        for (String city : cities) {
            try {
                URI url = uriBuilder(city);
                ResponseEntity<WeatherApiCurrentWeatherModel> exchange = restTemplate
                        .exchange(url, HttpMethod.GET, httpEntity, WeatherApiCurrentWeatherModel.class);
                if (exchange.getStatusCode() == HttpStatus.OK) {
                    toReturn.add(exchange.getBody());
                }
            } catch (Exception exception) {
                continue;
            }
        }
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    private URI uriBuilder(String city) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("key", Arrays.asList(apiKey));
        params.put("q", Arrays.asList(city));
        return UriComponentsBuilder.fromHttpUrl(coreURL).queryParams(params).encode().build().toUri();
    }
}
