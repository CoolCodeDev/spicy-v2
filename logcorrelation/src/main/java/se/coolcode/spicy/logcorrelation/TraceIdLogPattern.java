package se.coolcode.spicy.logcorrelation;

import se.coolcode.spicy.logger.LogEvent;
import se.coolcode.spicy.logger.LogPattern;
import se.coolcode.spicy.requestcontext.RequestContext;

public class TraceIdLogPattern implements LogPattern {

    @Override
    public String getKey() {
        return RequestContext.TRACE_ID;
    }

    @Override
    public String getValue(LogEvent logEvent) {
        return RequestContext.getInstance().getTraceId();
    }
}
