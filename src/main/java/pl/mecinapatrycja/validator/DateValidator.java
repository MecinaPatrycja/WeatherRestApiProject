package pl.mecinapatrycja.validator;
import org.apache.commons.lang3.StringUtils;
import pl.mecinapatrycja.exception.InvalidDateException;

import java.util.regex.Pattern;
public final class DateValidator {
    private DateValidator() {
        throw new IllegalStateException("Ten konstuktor jest prywatny");
    }

    public static void validate(String date) {
        String dateFormat = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(dateFormat);
        if (StringUtils.isEmpty(date) || !pattern.matcher(date).matches()) {
            throw new InvalidDateException();
        }
    }
}
