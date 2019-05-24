package pl.mecinapatrycja.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.mecinapatrycja.dto.LoginRequest;
import pl.mecinapatrycja.service.impl.UserLoginService;
@RestController
@RequiredArgsConstructor
public class UserLoginController {
    private final UserLoginService userLoginService;

    @PostMapping("/login")
    public String returnToken(@RequestBody LoginRequest loginRequest) {
        return userLoginService.returnLogin(loginRequest);
    }
}
