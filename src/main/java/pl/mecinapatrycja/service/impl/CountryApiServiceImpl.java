package pl.mecinapatrycja.service.impl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.mecinapatrycja.model.CountryApiModel;
import pl.mecinapatrycja.service.CountryApiService;
import pl.mecinapatrycja.validator.CountryCodeValidator;

import java.net.URI;
import java.text.Normalizer;
import java.util.Arrays;
@Service
public class CountryApiServiceImpl implements CountryApiService {
    @Value("${GEONAMES_CORE_URL}")
    private String coreURL;
    @Value("${GEONAMES_USERNAME}")
    private String username;

    private URI uriBuilder(String country) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("country", Arrays.asList(country));
        params.put("username", Arrays.asList(username));
        return UriComponentsBuilder.fromHttpUrl(coreURL).queryParams(params).encode().build().toUri();
    }

    public CountryApiModel getAllCitiesFromSpecificCountry(String code) {
        CountryCodeValidator.validate(code);
        CountryApiModel result;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CountryApiModel> exchange = restTemplate.getForEntity(uriBuilder(code), CountryApiModel.class);
        result = exchange.getBody();
        return result;
    }

    public static String stripAccents(String input) {
        return input == null ? null
                : Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("ł", "l")
                .replaceAll("Ł", "L");
    }
}
