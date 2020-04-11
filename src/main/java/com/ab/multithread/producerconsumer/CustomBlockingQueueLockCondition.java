package com.ab.multithread.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Arpit Bhardwaj
 *
 * Advantage of Lock over synchronized
 * scope of lock can range from one method to another but scope of synchronized keyword cannot go beyond one method.
 *
 * Disadvantage of Lock over synchronized
 * Unlike synchronized keyword, which acquire and release lock automatically
 * here to call lock() method to acquire the lock and unlock() method to release the lock,
 * failing to do will result in deadlock
 */
public class CustomBlockingQueueLockCondition<E> {

    private final int CAPACITY;
    private final Queue queue;

    public CustomBlockingQueueLockCondition(int capacity) {
        this.CAPACITY = capacity;
        this.queue = new LinkedList();
    }

    private final Lock lock = new ReentrantLock();
    private final Condition bufferFull = lock.newCondition();
    private final Condition bufferEmpty = lock.newCondition();

    public void put(E item) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == CAPACITY){
                bufferFull.await();
            }
            queue.add(item);
            bufferEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == 0){
                bufferEmpty.await();
            }
            E item = (E) queue.remove();
            bufferFull.signal();
            return item;
        }finally {
            lock.unlock();
        }
    }
}
