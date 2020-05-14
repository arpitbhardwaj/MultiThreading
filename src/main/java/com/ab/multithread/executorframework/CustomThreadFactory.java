package com.ab.multithread.executorframework;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Arpit Bhardwaj
 *
 * custom ThreadFactory implementation to customize the thread creation.
 * using same user can change the thread group, thread pool naming patterns, thread priorities and so on.
 */
public class CustomThreadFactory implements ThreadFactory {
    private String poolName;
    private ThreadGroup group;

    private AtomicInteger atomicInteger = new AtomicInteger();

    public CustomThreadFactory(String poolName) {
        this.poolName = poolName;
        SecurityManager s = new SecurityManager();
        this.group = Objects.nonNull(s) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new CustomThread(group,r,poolName,atomicInteger.incrementAndGet());
        if (t.isDaemon()){
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY){
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
