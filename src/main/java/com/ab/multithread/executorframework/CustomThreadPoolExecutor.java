package com.ab.multithread.executorframework;

import com.ab.multithread.model.CustomPoolWorkerThread;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Arpit Bhardwaj
 *
 */
public class CustomThreadPoolExecutor {
    private final int poolSize;
    private final CustomPoolWorkerThread[] workerThreads;
    public static final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    public CustomThreadPoolExecutor(int poolSize) {
        this.poolSize = poolSize;
        this.workerThreads = new CustomPoolWorkerThread[poolSize];

        for (int i = 0; i < poolSize; i++) {
            workerThreads[i] = new CustomPoolWorkerThread();
            workerThreads[i].start();
        }
    }

    public void execute(Runnable task){
        synchronized (queue){
            queue.add(task);
            queue.notify();
        }
    }

    public void shutdown(){
        System.out.println("Shutting down the thread pool");
        for (int i = 0; i < poolSize; i++) {
            workerThreads[i] = null;
        }
    }
}
