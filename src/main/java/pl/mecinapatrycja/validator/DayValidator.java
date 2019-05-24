package pl.mecinapatrycja.validator;
import org.apache.commons.lang3.StringUtils;
import pl.mecinapatrycja.exception.InvalidDayException;
public final class DayValidator {
    private DayValidator() {
        throw new IllegalStateException("Ten konstuktor jest prywatny");
    }

    public static void validate(String days) {
        final int number = 7;
        if (StringUtils.isEmpty(days) || !StringUtils.isNumeric(days) || Integer.parseInt(days) > number) {
            throw new InvalidDayException();
        }
    }
}
