package se.coolcode.spicy.requestcontext;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.UUID;

public class RequestContextServletFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (HttpServletRequest.class.isAssignableFrom(servletRequest.getClass())) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String traceId = getTraceId(request);
            RequestContext requestContext = RequestContext.getInstance();
            requestContext.setTraceId(traceId);
            requestContext.setTransactionId(UUID.randomUUID().toString());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTraceId(HttpServletRequest request) {
        String traceId = request.getHeader(RequestContext.TRACE_ID);
        return traceId != null ? traceId : UUID.randomUUID().toString();
    }
}
