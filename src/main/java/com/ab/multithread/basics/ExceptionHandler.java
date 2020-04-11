package com.ab.multithread.basics;

/**
 * @author Arpit Bhardwaj
 */
public class ExceptionHandler {
    public static void main(String[] args) {
        Thread.UncaughtExceptionHandler exceptionHandler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Exception occurred in thread : "
                + t.getName() + " Exception : " + e);
            }
        };

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Sleeping...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Throwing Exception...");
                throw new RuntimeException();
            }
        }, "Thread 1");

        t1.setUncaughtExceptionHandler(exceptionHandler);
        t1.start();
    }
}
