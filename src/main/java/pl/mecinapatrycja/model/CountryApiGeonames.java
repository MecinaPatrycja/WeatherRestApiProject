package pl.mecinapatrycja.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CountryApiGeonames {
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "countryName")
    private String countryName;
    @JsonProperty(value = "countryCode")
    private String code;
}
