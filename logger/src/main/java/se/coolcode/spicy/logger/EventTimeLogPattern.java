package se.coolcode.spicy.logger;

import java.time.format.DateTimeFormatter;

class EventTimeLogPattern implements LogPattern {
    private static final String key = "eventtime";
    private final DateTimeFormatter formatter;

    public EventTimeLogPattern(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue(LogEvent logEvent) {
        String value = "";
        if (logEvent != null && logEvent.eventTime() != null) {
            value = logEvent.eventTime().format(formatter);
        }
        return value;
    }
}
