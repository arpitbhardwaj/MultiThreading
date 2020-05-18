package com.ab.multithread.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Arpit Bhardwaj
 */

public class DeadlockDetector {
    private final DeadlockHandler deadlockHandler;
    private final long period;
    private final TimeUnit timeUnit;
    private final ThreadMXBean mxBean;
    private final ScheduledExecutorService schedulerService;

    public DeadlockDetector(DeadlockHandler handler, long period, TimeUnit timeUnit) {
        this.deadlockHandler = handler;
        this.period = period;
        this.timeUnit = timeUnit;
        this.mxBean = ManagementFactory.getThreadMXBean();
        this.schedulerService = Executors.newScheduledThreadPool(1);
    }

    final Runnable deadlockCheck = new Runnable() {
        @Override
        public void run() {
            long[] deadlockedThreads = mxBean.findDeadlockedThreads();
            if (deadlockedThreads != null){
                ThreadInfo[] threadInfo = mxBean.getThreadInfo(deadlockedThreads);
                deadlockHandler.handleDeadLock(threadInfo);
            }
        }
    };

    public void start(){
        this.schedulerService.scheduleAtFixedRate(this.deadlockCheck,this.period, this.period, this.timeUnit);
    }
}
