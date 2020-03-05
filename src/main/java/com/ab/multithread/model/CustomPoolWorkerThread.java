package com.ab.multithread.model;

import com.ab.multithread.executorframework.CustomThreadPoolExecutor;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Arpit Bhardwaj
 */
public class CustomPoolWorkerThread extends Thread{
    private static final LinkedBlockingQueue<Runnable> queue = CustomThreadPoolExecutor.queue;
    private Runnable task;

    @Override
    public void run() {
        while (true){
            synchronized (queue){
                while (queue.isEmpty()){
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                task = queue.poll();
            }

            try {
                task.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
