package se.coolcode.spicy.logger;

import java.time.LocalDateTime;

public record LogEvent(LocalDateTime eventTime, LogLevel logLevel, Class<?> type, String message) {

}
