package com.ab.multithread.executorframework;

/**
 * @author Arpit Bhardwaj
 */
public class CustomThread extends Thread{

    private static final String POOL_DELIMITER = "-";

    public CustomThread(ThreadGroup group, Runnable runnable, String poolName, int threadId) {
        super(group, runnable, String.format("%s%s%d",poolName, POOL_DELIMITER, threadId));
    }
}
