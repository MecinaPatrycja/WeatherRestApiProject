package pl.mecinapatrycja.exception;
public class InvalidCityNameException extends BaseException {
    public InvalidCityNameException() {
        super("Podaj prawidłową nazwę miasta");
    }
}
