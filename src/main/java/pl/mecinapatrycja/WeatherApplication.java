package pl.mecinapatrycja;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
@SpringBootApplication /*(exclude = {SecurityAutoConfiguration.class,
        UserDetailsServiceAutoConfiguration.class}) */
@EnableCaching
@RequiredArgsConstructor
public class WeatherApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }
}
