package pl.mecinapatrycja.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ForecastApiDay {
    private String date;
    @JsonProperty(value = "day")
    private ForecastDayApiInfo forecastDayApiInfo;
}
