package pl.mecinapatrycja.service.impl;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.idm.authorization.AuthorizationRequest;
import org.keycloak.representations.idm.authorization.AuthorizationResponse;
import org.springframework.stereotype.Service;
import pl.mecinapatrycja.dto.LoginRequest;
import pl.mecinapatrycja.exception.IncorrectLoginOrPasswordException;
@Service
public class UserLoginService {
    public String returnLogin(LoginRequest loginRequest) {
        AuthzClient authzClient = AuthzClient.create();
        AuthorizationRequest request = new AuthorizationRequest();
        AuthorizationResponse response;
        try {
            response = authzClient.authorization(loginRequest.getLogin(), loginRequest.getPassword()).authorize(request);
        } catch (Exception e) {
            throw new IncorrectLoginOrPasswordException();
        }
        return response.getToken();
    }
}
