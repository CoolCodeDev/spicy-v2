package se.coolcode.spicy.logger;

public class LogConfigurationException extends RuntimeException {

    public LogConfigurationException(String message, Exception e) {
        super(message, e);
    }
}
