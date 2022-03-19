package se.coolcode.spicy.logger;

class LogLevelLogPattern implements LogPattern {
    private static final String key = "loglevel";

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue(LogEvent logEvent) {
        String value = "";
        if(logEvent != null && logEvent.logLevel() != null) {
            value = logEvent.logLevel().name();
        }
        return value;
    }
}
