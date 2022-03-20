package se.coolcode.spicy.settings;

public class IntegerSetting extends AbstractSetting<Integer> {

    public IntegerSetting(String key, Integer value) {
        super(key, value);
    }

    public static Integer parse(String value, Property<Integer> property) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            String message = String.format("Value '%s' could not be parsed as integer for property %s.", value, property);
            throw new SettingsException(message, e);
        }
    }
}
