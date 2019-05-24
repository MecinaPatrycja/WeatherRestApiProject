package pl.mecinapatrycja.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ForecastApi {
    @JsonProperty(value = "forecastday")
    private List<ForecastApiDay> forecastDay;
}
