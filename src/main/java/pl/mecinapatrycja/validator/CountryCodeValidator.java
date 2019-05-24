package pl.mecinapatrycja.validator;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import pl.mecinapatrycja.constant.CountryCode;
import pl.mecinapatrycja.exception.InvalidCountryCodeException;
public final class CountryCodeValidator {
    private CountryCodeValidator() {
        throw new IllegalStateException("Ten konstuktor jest prywatny");
    }

    public static void validate(String code) {
        if (StringUtils.isEmpty(code) || !EnumUtils.isValidEnum(CountryCode.class, code)) {
            throw new InvalidCountryCodeException();
        }
    }
}
