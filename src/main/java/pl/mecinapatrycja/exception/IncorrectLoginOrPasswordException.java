package pl.mecinapatrycja.exception;
public class IncorrectLoginOrPasswordException extends BaseException {
    public IncorrectLoginOrPasswordException() {
        super("Niepoprawny login lub hasło");
    }
}
