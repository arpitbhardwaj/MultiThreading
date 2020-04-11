package com.ab.multithread.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Arpit Bhardwaj
 *
 * The BlockingQueue interface provides two methods put() and take()
 * which are used implicitly in blocking the Producer and the Consumer thread respectively.
 * The thread (Consumer) trying to remove item from an empty queue waits or is blocked
 * until the Producer thread adds an item to the queue.
 *
 * LinkedBlockingQueue : optionally-bounded blocking queue (The capacity, if unspecified, is equal to Integer.MAX_VALUE.)
 * ArrayBlockingQueue : bounded blocking queue
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {

        //final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        //final BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
        //final BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        final CustomBlockingQueueWaitNotify<String> queue = new CustomBlockingQueueWaitNotify<>(10);
        //final CustomBlockingQueueLockCondition<String> queue = new CustomBlockingQueueLockCondition<>(10);

        Runnable produceTask = new Runnable() {
            String produce = "item1";
            @Override
            public void run() {
                try {
                    queue.put(produce);
                    System.out.printf("[%s] published item : %s %n", Thread.currentThread().getName(),produce);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Done Producing !!");
            }
        };

        Runnable consumeTask = new Runnable() {
            @Override
            public void run() {
                try {
                    String producedItem = queue.take();
                    System.out.printf("[%s] consumed item : %s %n", Thread.currentThread().getName(),producedItem);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Done Consuming !!");
            }
        };

        Thread consumerThread = new Thread(consumeTask,"CONSUMER THREAD");
        Thread producerThread = new Thread(produceTask, "PRODUCER THREAD");

        consumerThread.start();
        producerThread.start();

    }
}

