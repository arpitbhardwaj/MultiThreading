package com.ab.multithread.executorframework;

import com.ab.multithread.model.Task;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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
        //ExecutorService executorService = Executors.newSingleThreadExecutor();

        //ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        //Executor executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            executorService.execute(new Task(""+i));
        }

        executorService.shutdown();
        while (!executorService.isTerminated()){}
        System.out.println("Finished all threads execution");
    }
}
