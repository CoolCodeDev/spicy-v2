package se.coolcode.spicy.settings;

public class SettingsException extends RuntimeException {

    public SettingsException(String message) {
        super(message);
    }

    public SettingsException(String message, Exception e) {
        super(message, e);
    }
}
