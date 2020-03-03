package com.ab.multithread.synchronizer;

import com.ab.multithread.model.Service;

import java.util.concurrent.CountDownLatch;

/**
 * @author Arpit Bhardwaj
 *
 * CountDownLatch can be used to implement multiple threads waiting for each other.
 * You can not reuse CountDownLatch once the count reaches zero
 *
 * CountDownLatch is a good for one-time events like application start-up time
 *
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(3);

        Thread cacheServiceThread = new Thread(new Service("Cache",1000,latch));
        Thread alertServiceThread = new Thread(new Service("Alert",1000,latch));
        Thread secureServiceThread = new Thread(new Service("Secure",1000,latch));

        cacheServiceThread.start();
        alertServiceThread.start();
        secureServiceThread.start();

        try {
            latch.await();
            System.out.println("All Services are up! Main Application is starting now");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
