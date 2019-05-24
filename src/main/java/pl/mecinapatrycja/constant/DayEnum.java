package pl.mecinapatrycja.constant;
import com.fasterxml.jackson.annotation.JsonValue;
public enum DayEnum {
    ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIFE("5"), SIX("6"), SEVEN("7");
    private final String day;

    DayEnum(String day) {
        this.day = day;
    }

    @JsonValue
    public String getDay() {
        return day;
    }
}
