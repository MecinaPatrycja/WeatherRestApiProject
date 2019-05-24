package pl.mecinapatrycja;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pl.mecinapatrycja.service.CountryApiService;
import pl.mecinapatrycja.service.CurrentWeatherApiService;
import pl.mecinapatrycja.service.ForecastApiService;
import pl.mecinapatrycja.service.MyCurrentWeatherApiService;
import pl.mecinapatrycja.service.MyForecastApiService;
@Profile("test")
@Configuration
public class TestConfiguration {
    @Bean
    @Primary
    public CountryApiService countryApiService() {
        return Mockito.mock(CountryApiService.class);
    }

    @Bean
    @Primary
    public CurrentWeatherApiService currentWeatherApiService() {
        return Mockito.mock(CurrentWeatherApiService.class);
    }

    @Bean
    @Primary
    public ForecastApiService forecastApiService() {
        return Mockito.mock(ForecastApiService.class);
    }

    @Bean
    @Primary
    public MyCurrentWeatherApiService myCurrentWeatherApiService() {
        return Mockito.mock(MyCurrentWeatherApiService.class);
    }

    @Bean
    @Primary
    public MyForecastApiService myForecastApiService() {
        return Mockito.mock(MyForecastApiService.class);
    }
}
