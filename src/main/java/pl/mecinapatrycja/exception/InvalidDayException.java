package pl.mecinapatrycja.exception;
public class InvalidDayException extends BaseException {
    public InvalidDayException() {
        super("Podaj cyfrę z zakresu: 1-7. Prognoza dostępna jest na najbliższy tydzień");
    }
}
