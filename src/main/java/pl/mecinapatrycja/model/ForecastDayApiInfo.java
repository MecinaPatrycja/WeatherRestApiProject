package pl.mecinapatrycja.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ForecastDayApiInfo {
    @JsonProperty(value = "maxtemp_c")
    private double maxTemp;
    @JsonProperty(value = "mintemp_c")
    private double minTemp;
    @JsonProperty(value = "maxwind_kph")
    private double maxWind;
    @JsonProperty(value = "condition")
    private ForecastApiCondition forecastApiCondition;
}
