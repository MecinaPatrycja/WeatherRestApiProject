package pl.mecinapatrycja.validator;
import org.apache.commons.lang3.StringUtils;
import pl.mecinapatrycja.exception.InvalidCityNameException;
public final class CityNameValidator {
    private CityNameValidator() {
        throw new IllegalStateException("Ten konstuktor jest prywatny");
    }

    public static void validate(String city) {
        if (StringUtils.isEmpty(city) || StringUtils.isNumeric(city) || !city.chars().allMatch(Character::isLetter)) {
            throw new InvalidCityNameException();
        }
    }
}
