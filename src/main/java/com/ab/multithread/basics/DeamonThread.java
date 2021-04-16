package com.ab.multithread.basics;

/**
 * @author Arpit Bhardwaj
 *
 * As soon as last non daemon thread finished JVM terminates no matter how many Daemon thread exists or running inside JVM
 * It is an utmost low priority thread.
 *
 */
public class DeamonThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                try {
                    while (true){
                        System.out.println("Running" + name);
                    }
                }
                finally {
                    System.out.println("Ending" + name);
                }

            }
        });
        //throw IllegalThreadStateException if corresponding Thread is already started and running.

        t.setName("Demon Thread 1");
        t.start();

        Thread.sleep(1);
        t.setDaemon(true);
    }
}
