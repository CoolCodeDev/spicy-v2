package se.coolcode.spicy.concurrency;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {
    private final AtomicInteger count = new AtomicInteger();
    private final String name;
    private final ThreadGroup parentThreadGroup;

    public NamedThreadFactory(String name) {
        this(name, Thread.currentThread().getThreadGroup());
    }

    public NamedThreadFactory(String name, ThreadGroup parentThreadGroup) {
        this.name = name;
        this.parentThreadGroup = parentThreadGroup;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        String threadName = String.format("%s-thread-%d", name, count.incrementAndGet());
        return new Thread(parentThreadGroup, runnable, threadName, 0, true);
    }
}
