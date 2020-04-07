package com.ab.multithread.executorframework;

import com.ab.multithread.model.Task;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Arpit Bhardwaj
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
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
