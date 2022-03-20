package se.coolcode.spicy.requestcontext;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class RequestContextAwareExecutorService implements ExecutorService {

    private final ExecutorService executorService;

    public RequestContextAwareExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void shutdown() {
        this.executorService.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return this.executorService.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return this.executorService.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return this.executorService.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return this.executorService.awaitTermination(timeout, unit);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return this.executorService.submit(RequestContextAwareTask.create(task));
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return this.executorService.submit(RequestContextAwareTask.create(task), result);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return this.executorService.submit(RequestContextAwareTask.create(task));
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return this.executorService.invokeAll(RequestContextAwareTask.create(tasks));
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return this.executorService.invokeAll(RequestContextAwareTask.create(tasks), timeout, unit);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return this.executorService.invokeAny(RequestContextAwareTask.create(tasks));
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.executorService.invokeAny(RequestContextAwareTask.create(tasks), timeout, unit);
    }

    @Override
    public void execute(Runnable command) {
        this.executorService.execute(RequestContextAwareTask.create(command));
    }
}
