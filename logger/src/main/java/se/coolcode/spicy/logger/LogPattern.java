package se.coolcode.spicy.logger;

public interface LogPattern {
    String getKey();
    String getValue(LogEvent logEvent);
}
