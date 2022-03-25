package se.coolcode.spicy.settings;

public class StringSetting extends AbstractSetting<String> {

    public StringSetting(String key, String value) {
        super(key, value);
    }

    @Override
    String parse(String value) {
        return value;
    }

}
