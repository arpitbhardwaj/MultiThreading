package com.ab.multithread.basics;

import com.ab.multithread.model.LongWrapper;

public class RaceCondition {

    public static void main(String[] args) throws InterruptedException {
        LongWrapper longWrapper = new LongWrapper(0L);

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                longWrapper.incrementL();
            }
        };

        /*Thread t = new Thread(task);
        t.start();
        t.join();*/

        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println("Value = " + longWrapper.getL());
    }
}
