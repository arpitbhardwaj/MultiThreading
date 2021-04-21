package com.ab.multithread.executorframework;

import com.ab.multithread.model.Task;

import java.util.concurrent.*;

/**
 * @author Arpit Bhardwaj
 * java.util.concurrent.ExecutorService interface is the extension of java.util.concurrent.Executor interface.
 * The java.util.concurrent.ThreadPoolExecutor is an implementation of the java.util.concurrent.ExecutorService interface.
 *
 * Executors is a utility class for factoru and utility methods for ExecutorService similar to Arrays and Collections
 *
 * If no custom thread factory instance is passed Java creates the threads with its internal thread factory.
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        //ExecutorService executorService = Executors.newFixedThreadPool(2);
        ExecutorService executorService = Executors.newFixedThreadPool(3,new CustomThreadFactory("CustomPool"));
        //ExecutorService executorService = Executors.newCachedThreadPool();

        //newSingleThreadExecutor() is equivalent to newFixedThreadPool(1)
        //ExecutorService executorService = Executors.newSingleThreadExecutor();

        //ExecutorService executorService = Executors.newScheduledThreadPool(2);
        //ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();


        //ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        //Executor executor = Executors.newFixedThreadPool(5);

        //execute method is from Executor interface which accepts runnable but return void
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Task(""+i));
        }

        //The shutdown() method allows existing tasks to finish and prevents new tasks from being added.
        executorService.shutdown();

        //The shutdownNow() method attempts to stop all running tasks and prevents new tasks from being added.
        //The shutdownNow() returns the list of waiting task.
        //executorService.shutdownNow();

        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //while (!executorService.isTerminated()){}
        System.out.println("Finished all threads execution");
    }
}
