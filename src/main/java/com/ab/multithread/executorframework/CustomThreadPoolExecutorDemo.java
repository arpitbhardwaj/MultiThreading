package com.ab.multithread.executorframework;

import com.ab.multithread.model.Task;

/**
 * @author Arpit Bhardwaj
 */
public class CustomThreadPoolExecutorDemo {
    public static void main(String[] args) throws InterruptedException {
        CustomThreadPoolExecutor customThreadPool = new CustomThreadPoolExecutor(5);
        for (int i = 0; i < 10; i++) {
            customThreadPool.execute(new Task(""+i));
        }
        Thread.sleep(5000);
        customThreadPool.shutdown();
        /*while (!customThreadPool.isTerminated()){}
        System.out.println("Finished all threads execution");*/
    }
}
