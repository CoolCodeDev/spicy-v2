package se.coolcode.spicy.settings;

public class IntegerSetting extends AbstractSetting<Integer> {

    public IntegerSetting(String key, String value) {
        super(key, value);
    }

    public Integer parse(String value, String key) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            String message = String.format("Value '%s' could not be parsed as integer for property %s.", value, key);
            throw new SettingsException(message, e);
        }
    }
}
