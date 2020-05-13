package com.ab.multithread.basics;

/**
 * @author Arpit Bhardwaj
 *
 * Race condition in Java is a type of concurrency bug or issue which is introduced in your program
 * because  parallel execution of your program by multiple threads
 *
 * Classical example of Race condition is incrementing a counter
 * since increment is not an atomic operation and can be further divided into three steps like read, update and write.
 *
 */

public class RaceCondition {

    private static class LongWrapper {
        private long l;
        private Object key = new Object();
        public LongWrapper(long l) {
            this.l = l;
        }

        public long getL() {
            return l;
        }

        public void incrementL() {
            synchronized (key){
                l = l + 1 ;
            }
        }
    }

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
