package se.coolcode.spicy.requestcontext;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

class RequestContextAwareTask<V> implements Callable<V>, Runnable {

    private final RequestContext requestContext;
    private Runnable runnable;
    private Callable<V> callable;

    private RequestContextAwareTask(Callable<V> callable) {
        this.requestContext = RequestContext.getInstance();
        this.callable = callable;
    }

    private RequestContextAwareTask(Runnable runnable) {
        this.requestContext = RequestContext.getInstance();
        this.runnable = runnable;
    }

    @Override
    public V call() throws Exception {
        try {
            RequestContext.set(this.requestContext);
            return this.callable.call();
        } finally {
            RequestContext.remove();
        }
    }

    @Override
    public void run() {
        try {
            RequestContext.set(this.requestContext);
            this.runnable.run();
        } finally {
            RequestContext.remove();
        }
    }

    public static Runnable create(Runnable task) {
        return new RequestContextAwareTask<>(task);
    }

    public static <V> Callable<V> create(Callable<V> callable) {
        return new RequestContextAwareTask<>(callable);
    }

    public static <V> Collection<Callable<V>> create(Collection<? extends Callable<V>> callables) {
        return callables.stream().map(RequestContextAwareTask::create).collect(Collectors.toList());
    }
}
