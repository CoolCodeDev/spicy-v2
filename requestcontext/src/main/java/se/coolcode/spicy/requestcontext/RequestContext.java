package se.coolcode.spicy.requestcontext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RequestContext {
    public static final String CLIENT_ID = "client.id";
    public static final String TRACE_ID = "trace.id";
    public static final String TRANSACTION_ID = "transaction.id";
    private static final RequestContextThreadLocal requestContextThreadLocal = new RequestContextThreadLocal();
    private  final Map<String, String> values = new ConcurrentHashMap<>();

    private RequestContext() {}

    public static RequestContext getInstance() {
        return RequestContext.requestContextThreadLocal.get();
    }

    public static void remove() {
        requestContextThreadLocal.remove();
    }

    public String getClientId() {
        return values.get(CLIENT_ID);
    }

    public String getTraceId() {
        return values.get(TRACE_ID);
    }

    public String getTransactionId() {
        return values.get(TRANSACTION_ID);
    }

    public void setClientId(String clientId) {
        values.put(CLIENT_ID, clientId);
    }

    public void setTraceId(String traceId) {
        values.put(TRACE_ID, traceId);
    }

    public void setTransactionId(String transactionId) {
        values.put(TRANSACTION_ID, transactionId);
    }

    private static class RequestContextThreadLocal extends InheritableThreadLocal<RequestContext> {

        @Override
        protected RequestContext initialValue() {
            return new RequestContext();
        }
    }
}
