package se.coolcode.spicy.logger;

public class LoggerFactory {

    public static Logger getLogger(Class<?> type) {
        return new SyncLogger(type, LogConfiguration.INSTANCE);
    }

    public static Logger getAsyncLogger(Class<?> type) {
        return new AsyncLogger(type, LogConfiguration.INSTANCE);
    }
}
