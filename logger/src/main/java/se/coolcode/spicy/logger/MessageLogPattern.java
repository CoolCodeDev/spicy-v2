package se.coolcode.spicy.logger;

class MessageLogPattern implements LogPattern {
    private static final String key = "message";

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue(LogEvent logEvent) {
        String value = "";
        if (logEvent != null && logEvent.message() != null) {
            value = logEvent.message();
        }
        return value;
    }
}
