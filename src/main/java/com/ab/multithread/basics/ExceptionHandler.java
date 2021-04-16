package com.ab.multithread.basics;

/**
 * @author Arpit Bhardwaj
 * Exception occured in child thread does not end the parent Thread
 *
 * UncaughtExceptionHandler can be used for making logging more robust only as well without restarting the thread
 *
 * (1) Thread.setUncaughtExceptionHandler set the exception handler for the current thread
 * (2) Thread.setDefaultUncaughtExceptionHandler set the default exception handler for the entire
 */
public class ExceptionHandler {
    public static void main(String[] args) {
        Thread.UncaughtExceptionHandler exceptionHandler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + " Exception : " + e);
            }
        };

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Sleeping...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Throwing Exception...");
                throw new RuntimeException();
            }
        }, "Thread 1");
        //set exception handler only for Thread 1
        //t1.setUncaughtExceptionHandler(exceptionHandler);
        //set the default exception handler for the entire
        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
        t1.start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
