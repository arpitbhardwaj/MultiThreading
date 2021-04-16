package com.ab.multithread.basics;

import com.sun.org.apache.xpath.internal.operations.Mult;

/**
 * @author Arpit Bhardwaj
 *
 * Threads can be created by using two mechanisms :
 *
 * Extending the Thread class
 * Implementing the Runnable Interface (Recommended)
 *
 * If we extend the Thread class, our class cannot extend any other class because Java doesn’t support multiple inheritance
 * Using runnable will give you an object that can be shared amongst multiple threads.
 *
 * join(): It will put the current thread on wait until the thread on which it is called is dead.
 * join(long millis) :It will put the current thread on wait until the thread on which it is called is dead or wait for specified time
 * If thread is interrupted then join methods will throw InterruptedException.
 */
public class FirstRunnable {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is running");

        Runnable runnable1 = ()-> {System.out.println("I am running in " + Thread.currentThread().getName());};
        Thread t1 = new Thread(runnable1);
        t1.setName("Thread t1");
        //The purpose of start() is to create a separate call stack for the thread.
        // A separate call stack is created by it, and then run() is called by JVM.
        t1.start();
        //t1.run(); //not run as a separate thread instead as part of main thread

        MultiThread1 t2 = new MultiThread1();
        t2.setName("Thread t2");
        t2.start();
        t2.join();

        Runnable runnable2 = new MultiThread2();
        Thread t3 = new Thread(runnable2);
        t3.setName("Thread t3");
        t3.start();
        t3.join(10);

        System.out.println(Thread.currentThread().getName() + " is finished");
    }

    static class MultiThread1 extends Thread{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " is finished");
        }
    }

    static class MultiThread2 implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " is finished");
        }
    }
}
