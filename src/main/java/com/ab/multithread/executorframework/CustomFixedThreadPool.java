package com.ab.multithread.executorframework;

import com.ab.multithread.model.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Arpit Bhardwaj
 *
 * Fixed thread pool is initialized with user supplied number of threads (nThreads).
 * At any point in time, this thread pool contains nThreads no of threads.
 * It internally manages a LinkedBlockingQueue to queue up additional influx of tasks when all threads in the pool are busy and new tasks can not be processed immediately.
 *
 */
public class CustomFixedThreadPool {

    private final int poolSize;
    private final CustomPoolWorkerThread[] workerThreads;
    public static final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    private static class CustomPoolWorkerThread extends Thread{
        private Runnable task;
        @Override
        public void run() {
            while (true){
                try {
                    task = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public CustomFixedThreadPool(int poolSize) {
        this.poolSize = poolSize;
        this.workerThreads = new CustomPoolWorkerThread[poolSize];

        for (int i = 0; i < poolSize; i++) {
            workerThreads[i] = new CustomPoolWorkerThread();
            workerThreads[i].start();
        }
    }

    public void execute(Runnable task){
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown(){
        System.out.println("Shutting down the thread pool");
        for (int i = 0; i < poolSize; i++) {
            workerThreads[i] = null;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CustomFixedThreadPool customThreadPool = new CustomFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            customThreadPool.execute(new Task(""+i));
        }
        Thread.sleep(5000);
        customThreadPool.shutdown();
        /*while (!customThreadPool.isTerminated()){}
        System.out.println("Finished all threads execution");*/
    }
}
