package se.coolcode.spicy.logcorrelation;

import se.coolcode.spicy.logger.LogEvent;
import se.coolcode.spicy.logger.LogPattern;
import se.coolcode.spicy.requestcontext.RequestContext;

public class ClientIdLogPattern implements LogPattern {

    @Override
    public String getKey() {
        return RequestContext.CLIENT_ID;
    }

    @Override
    public String getValue(LogEvent logEvent) {
        return RequestContext.getInstance().getClientId();
    }
}
