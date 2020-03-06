package com.ab.multithread.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author Arpit Bhardwaj
 *
 * SynchronousQueue is special kind of BlockingQueue
 * in which each insert operation must wait for a corresponding remove operation by another thread, and vice versa
 *
 * When you call put() method on SynchronousQueue it blocks until another thread is there to take that element out of the Queu
 * Similarly, if a thread tries to remove an element and no element is currently present, that thread is blocked until another thread puts an element into the queue.
 *
 * This queue does not permit null elements, adding null elements will result in NullPointerException.
 * You cannot peek at a synchronous queue because an element is only present when you try to remove it; Similarly you cannot insert an element (using any method) unless another thread is trying to remove it.
 * A SynchronousQueue constructed with fairness policy set to true grants threads access in FIFO order
 *
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        final SynchronousQueue<String> queue = new SynchronousQueue<>(true);
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
