package se.coolcode.spicy.logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public abstract class AbstractLogger implements Logger {

    private final Class<?> type;
    private LogConfiguration configuration;

    AbstractLogger(Class<?> type, LogConfiguration configuration) {
        this.type = type;
        this.configuration = configuration;
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    private void log(LogLevel logLevel, String message) {
        log(new LogEvent(LocalDateTime.now(), logLevel, type, message));
    }

    protected abstract void log(LogEvent logEvent);

    byte[] createLogEntry(LogEvent logEvent) {
        return configuration.json ? toJson(logEvent) : toPlainText(logEvent);
    }

    private byte[] toJson(LogEvent logEvent) {
        return configuration.patterns.stream()
                .collect(Collectors.toMap(LogPattern::getKey, pattern -> pattern.getValue(logEvent), (s1, s2) -> s2))
                .entrySet().stream()
                .map(entry -> String.format("\"%s\": \"%s\"", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", ", "{", "}"))
                .getBytes(StandardCharsets.UTF_8);
    }

    private byte[] toPlainText(LogEvent logEvent) {
        return configuration.patterns.stream()
                .map(pattern -> pattern.getValue(logEvent))
                .collect(Collectors.joining(" ", "", "\n"))
                .getBytes(StandardCharsets.UTF_8);
    }

    void log(byte[] logEntry) {
        configuration.outputs.forEach(outputStream -> {
            try {
                synchronized (outputStream) {
                    outputStream.write(logEntry);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
