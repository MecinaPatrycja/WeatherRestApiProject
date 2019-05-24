//package pl.mecinapatrycja.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import pl.mecinapatrycja.jwt.JwtTokenDecoder;
//
//import java.util.List;
//@Configuration
//public class WebMvcDecoderConfiguration implements WebMvcConfigurer {
//
//    private final JwtTokenDecoder jwtTokenDecoder;
//
//    public WebMvcDecoderConfiguration(JwtTokenDecoder jwtTokenDecoder) {
//        this.jwtTokenDecoder = jwtTokenDecoder;
//    }
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        resolvers.add(jwtTokenDecoder);
//    }
//}
