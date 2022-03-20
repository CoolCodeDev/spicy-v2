package se.coolcode.spicy.logger;

import se.coolcode.spicy.concurrency.NamedThreadFactory;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AsyncLogger extends AbstractLogger {

    private final ExecutorService executor;
    private final Queue<LogEvent> logEventQueue = new ConcurrentLinkedDeque<>();

    AsyncLogger(Class<?> type, LogConfiguration configuration) {
        super(type, configuration);
        this.executor = Executors.newSingleThreadExecutor(new NamedThreadFactory("async-logger"));
        this.executor.submit(logTask());
    }

    @Override
    protected void log(LogEvent logEvent) {
        logEventQueue.add(logEvent);
    }

    private Runnable logTask() {
        return () -> {
            while (logEventQueue.peek() != null) {
                LogEvent logEvent = logEventQueue.poll();
                byte[] logEntry = createLogEntry(logEvent);
                log(logEntry);
            }
        };
    }

    public void close() {
        executor.shutdown();
        int timeout = 10;
        try {
            if (!executor.awaitTermination(timeout, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                if (!executor.awaitTermination(timeout, TimeUnit.SECONDS))
                    System.err.println("Executor did not terminate.");
            }
        } catch (InterruptedException ie) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
