package se.coolcode.spicy.requestcontext;

public class RequestContext {

    private static final RequestContextThreadLocal requestContextThreadLocal = new RequestContextThreadLocal();

    private RequestContext() {}

    public static RequestContext getInstance() {
        return RequestContext.requestContextThreadLocal.get();
    }

    public static void remove() {
        requestContextThreadLocal.remove();
    }

    private static class RequestContextThreadLocal extends ThreadLocal<RequestContext> {

        @Override
        protected RequestContext initialValue() {
            return new RequestContext();
        }
    }
}
