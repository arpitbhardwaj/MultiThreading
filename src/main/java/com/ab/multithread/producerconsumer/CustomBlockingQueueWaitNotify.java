package com.ab.multithread.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Arpit Bhardwaj
 */
public class CustomBlockingQueueWaitNotify<E> {
    private final int CAPACITY;
    private final Queue queue;

    public CustomBlockingQueueWaitNotify(int capacity) {
        this.CAPACITY = capacity;
        this.queue = new LinkedList();
    }

    public synchronized void put(E item) throws InterruptedException {
        while (queue.size() == CAPACITY){
            this.wait();
        }
        queue.add(item);
        this.notify();
    }

    public synchronized E take() throws InterruptedException {
        while (queue.size() == 0){
            this.wait();
        }
        E item = (E) queue.remove();
        this.notify();
        return item;
    }
}
