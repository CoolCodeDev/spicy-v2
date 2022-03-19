package se.coolcode.spicy.logger;

class LoggerLogPattern implements LogPattern {
    private static final String key = "logger";

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue(LogEvent logEvent) {
        String value = "";
        if (logEvent != null && logEvent.type() != null) {
            value = logEvent.type().getName();
        }
        return value;
    }
}
