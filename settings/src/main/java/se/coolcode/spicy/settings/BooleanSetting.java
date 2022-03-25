package se.coolcode.spicy.settings;

public class BooleanSetting extends AbstractSetting<Boolean> {

    public BooleanSetting(String key, String value) {
        super(key, value);
    }

    public Boolean parse(String value, String key) {
        Boolean parsedValue = Boolean.parseBoolean(value);
        if (parsedValue.toString().equals(value)) {
            return parsedValue;
        } else {
            String message = String.format("Value '%s' could not be parsed as boolean for property %s.", value, key);
            throw new SettingsException(message);
        }
    }
}
