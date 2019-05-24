package pl.mecinapatrycja.service;
import pl.mecinapatrycja.model.CountryApiModel;
public interface CountryApiService {
    CountryApiModel getAllCitiesFromSpecificCountry(String countryCode);
}
