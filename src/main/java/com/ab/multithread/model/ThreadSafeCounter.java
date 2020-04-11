package com.ab.multithread.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Arpit Bhardwaj
 *
 * use Atomic Integer, which makes this ++ operation atomic and since atomic operations are thread-safe
 * and saves cost of external synchronization.
 */
public class ThreadSafeCounter {
    private AtomicInteger count = new AtomicInteger(0);

    public int getCount(){
        return count.incrementAndGet();
    }
}
