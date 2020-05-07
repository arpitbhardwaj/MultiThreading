package com.ab.multithread.future;

import java.util.concurrent.*;

/**
 * @author Arpit Bhardwaj
 */
public class Differences {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnableTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside Runnable run method");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Callable<String> callableTask = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Inside Callable call method");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Callable";
            }
        };

        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Inside Future Callable call method");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "FutureTask";
            }
        });

        Future runnableSubmit = executorService.submit(runnableTask);
        Future<String> callableSubmit = executorService.submit(callableTask);
        //When you submit a FutureTask to a ExecutorService it is treated as a Runnable
        //so Future you get from the submit method would have the value null as the Runnable instances don't return any result.
        Future futureSubmit = executorService.submit(futureTask);

        while (!runnableSubmit.isDone()){
            System.out.println("Runnable Task is not completed yet...");
            Thread.sleep(50);
        }
        while (!callableSubmit.isDone()){
            System.out.println("Callable Task is not completed yet...");
            Thread.sleep(100);
        }
        while (!futureSubmit.isDone()){
            System.out.println("Future Task is not completed yet...");
            Thread.sleep(150);
        }
        System.out.println("Runnable Task : " + runnableSubmit.get());
        System.out.println("Callable Task : " + callableSubmit.get());
        System.out.println("Future Task : " + futureSubmit.get());
        executorService.shutdown();
    }
}
