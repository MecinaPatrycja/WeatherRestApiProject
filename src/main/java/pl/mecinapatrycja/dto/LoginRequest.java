package pl.mecinapatrycja.dto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@RequiredArgsConstructor
@Getter
@Setter
public class LoginRequest {
    private String login;
    private String password;
}
