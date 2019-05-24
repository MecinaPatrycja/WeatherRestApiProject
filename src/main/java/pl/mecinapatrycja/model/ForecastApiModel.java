package pl.mecinapatrycja.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ForecastApiModel {
    @JsonProperty("current")
    private WeatherApiCurrentModel currentModel;
    @JsonProperty("location")
    private WeatherApiLocationModel locationModel;
    @JsonProperty("forecast")
    private ForecastApi forecast;
}

