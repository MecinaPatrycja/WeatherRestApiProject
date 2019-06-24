package pl.mecinapatrycja.jwt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
@Component
public class JwtTokenDecoder implements HandlerMethodArgumentResolver {
    private final JwtDecoder jwtDecoder;

    public JwtTokenDecoder(@Qualifier("jwtDecoderByJwkKeySetUri") JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JwtData.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        return decode(webRequest.getHeader(HttpHeaders.AUTHORIZATION));
    }

    public JwtTokenUserData decode(String authorizationHeader) {
        Jwt jwtToken = decodeToken(authorizationHeader);
        Map<String, Object> claims = jwtToken.getClaims();
        String name = (String) claims.get("name");
        String username = (String) claims.get("preferred_username");
        String email = (String) claims.get("email");
        return new JwtTokenUserData(name, username, email);
    }

    private Jwt decodeToken(String token) {
        try {
            return jwtDecoder.decode(token.replace("Bearer", ""));
        } catch (JwtException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to read JWT token");
        }
    }
}
