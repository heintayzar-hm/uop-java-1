package src.assi_4;
import java.util.ArrayList;
import java.util.List;

public class Validator {
    private String value;
    private boolean isValid;
    private List<String> errorMessages;

    private Validator(String value) {
        this.value = value;
        this.isValid = true;
        this.errorMessages = new ArrayList<>();
    }


    public static Validator forValue() {
        return new Validator("");
    }

    public static Validator forValue(String value) {
        return new Validator(value);
    }

    public static Validator forValue(int value) {
        return new Validator(String.valueOf(value));
    }

    public Validator setValue(String value) {
        this.value = value;
        return this;
    }

    public Validator setValue(int value) {
        this.value = String.valueOf(value);
        return this;
    }


    public String getValue() {
        return value;
    }

    public Validator isNotNull() {
        isValid = isValid && value != null;
        return this;
    }

    public Validator isNotNull(String errorMessage) {
        isValid = isValid && value != null;
        if (!isValid) {
            errorMessages.add(errorMessage);
        }
        return this;
    }

    public Validator isNumeric() {
        isValid = isValid && value != null && value.matches("\\d+");
        return this;
    }

    public Validator isNumeric(String errorMessage) {
        isValid = isValid && value != null && value.matches("\\d+");
        if (!isValid) {
            errorMessages.add(errorMessage);
        }
        return this;
    }

    public Validator isNotEmpty() {
        isValid = isValid && value != null && !value.isEmpty();
        return this;
    }

    public Validator isNotEmpty(String errorMessage) {
        isValid = isValid && value != null && !value.isEmpty();
        if (!isValid) {
            errorMessages.add(errorMessage);
        }
        return this;
    }

    public Validator hasLengthBetween(int minLength, int maxLength) {
        isValid = isValid && value != null && value.length() >= minLength && value.length() <= maxLength;
        return this;
    }

    public Validator hasLengthBetween(int minLength, int maxLength, String errorMessage) {
        isValid = isValid && value != null && value.length() >= minLength && value.length() <= maxLength;
        if (!isValid) {
            errorMessages.add(errorMessage);
        }
        return this;
    }

    public Validator isGreaterThan(int value) {
        isValid = isValid && Integer.parseInt(this.value) > value;
        return this;
    }

    public Validator isGreaterThan(int value, String errorMessage) {
        isValid = isValid && Integer.parseInt(this.value) > value;
        if (!isValid) {
            errorMessages.add(errorMessage);
        }
        return this;
    }

    public Validator isLessThan(int value) {
        isValid = isValid && Integer.parseInt(this.value) < value;
        return this;
    }

    public Validator isLessThan(int value, String errorMessage) {
        isValid = isValid && Integer.parseInt(this.value) < value;
        if (!isValid) {
            errorMessages.add(errorMessage);
        }
        return this;
    }

    public Validator isBetween(int minValue, int maxValue) {
        isValid = isValid && Integer.parseInt(this.value) >= minValue && Integer.parseInt(this.value) <= maxValue;
        return this;
    }

    public Validator isBetween(int minValue, int maxValue, String errorMessage) {
        isValid = isValid && Integer.parseInt(this.value) >= minValue && Integer.parseInt(this.value) <= maxValue;
        if (!isValid) {
            errorMessages.add(errorMessage);
        }
        return this;
    }

    public Validator unique(List<String> values) {
        isValid = isValid && !values.contains(value);
        return this;
    }

    public Validator unique(String errorMessage, List<String> values) {
        isValid = isValid && !values.contains(value);
        if (!isValid) {
            errorMessages.add(errorMessage);
        }
        return this;
    }

    // Add more validation methods as needed

    public boolean isValid() {
        return isValid;
    }


    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
