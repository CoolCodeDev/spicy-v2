package se.coolcode.spicy.settings;

public class BooleanSetting extends AbstractSetting<Boolean> {

    public BooleanSetting(String key, Boolean value) {
        super(key, value);
    }

    public static Boolean parse(String value, Property<Boolean> property) {
        Boolean parsedValue = Boolean.parseBoolean(value);
        if (parsedValue.toString().equals(value)) {
            return parsedValue;
        } else {
            String message = String.format("Value '%s' could not be parsed as boolean for property %s.", value, property);
            throw new SettingsException(message);
        }
    }
}
