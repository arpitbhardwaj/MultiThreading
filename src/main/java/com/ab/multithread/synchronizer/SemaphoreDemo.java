package com.ab.multithread.synchronizer;

import java.util.concurrent.Semaphore;

/**
 * @author Arpit Bhardwaj
 *
 * Counting Semaphore in Java maintains specified number of pass or permits, In order to access a shared resource
 * Current Thread must acquire a permit. If permit is already exhausted by other thread
 * than it can wait until a permit is available due to release of permit from different thread.
 *
 * a Counting semaphore with one permit is known as binary semaphore
 * because it has only two state permit available or permit unavailable.
 *
 * Binary semaphore can be used to implement mutual exclusion or critical section where only one thread is allowed to execute
 */
public class SemaphoreDemo {

    Semaphore binarySemaphore = new Semaphore(1);//binary semaphore

    public static void main(String[] args) {
        SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                semaphoreDemo.mutualExclusionCode();
            }
        }, "Thread 1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                semaphoreDemo.mutualExclusionCode();
            }
        }, "Thread 2");

        t1.start();
        t2.start();
    }

    public void mutualExclusionCode(){
        try {
            binarySemaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " inside mutual exclusive code");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            binarySemaphore.release();
            System.out.println(Thread.currentThread().getName() + " outside mutual exclusive code");
        }
    }
}
