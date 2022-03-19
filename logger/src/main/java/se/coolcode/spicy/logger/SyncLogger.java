package se.coolcode.spicy.logger;

public class SyncLogger extends AbstractLogger {

    SyncLogger(Class<?> type, LogConfiguration configuration) {
        super(type, configuration);
    }

    @Override
    protected void log(LogEvent logEvent) {
        byte[] logEntry = createLogEntry(logEvent);
        log(logEntry);
    }

}
