package pl.mecinapatrycja.exception;
public class InvalidDateException extends BaseException {
    public InvalidDateException() {
        super("Podaj datę w prawidłowym formacie: YYYY-MM-DD. Prognoza dostępna jest na najbliższy tydzień");
    }
}
