package pl.mecinapatrycja.exception;
public class InvalidCountryCodeException extends BaseException {
    public InvalidCountryCodeException() {
        super("Podaj prawidłowy kod kraju");
    }
}
