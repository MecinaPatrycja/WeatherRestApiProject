package pl.mecinapatrycja.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mecinapatrycja.service.impl.CountryApiServiceImpl;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class CountryApiModel {
    private List<CountryApiGeonames> geonames;

    public List<String> getCities() {
        List<String> cities = new ArrayList<>();
        for (CountryApiGeonames cag : geonames) {
            cities.add(CountryApiServiceImpl.stripAccents(cag.getName()));
        }
        return cities;
    }

    public String getCountryName() {
        return geonames.get(0).getCountryName();
    }
}
