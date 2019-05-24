package pl.mecinapatrycja.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
@Getter
@Setter
public class WeatherApiCurrentModel {
    @Value("condition")
    private WeatherApiConditionModel condition;
    @JsonProperty("last_updated")
    private String lastUpdate;
    @JsonProperty("temp_c")
    private double temperatureInCelsius;
    @JsonProperty("wind_kph")
    private double wind;
    @JsonProperty("pressure_mb")
    private int pressure;
    private String humidity;
    @JsonProperty("feelslike_c")
    private double feelsLike;
    private double uv;
}
