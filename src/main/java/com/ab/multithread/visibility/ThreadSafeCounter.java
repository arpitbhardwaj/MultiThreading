package com.ab.multithread.visibility;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Arpit Bhardwaj
 *
 * use Atomic Integer, which makes this ++ operation atomic and since atomic operations are thread-safe
 * and saves cost of external synchronization.
 *
 * Internally, the atomic classes make heavy use of compare-and-swap (CAS),
 * an atomic instruction directly supported by most modern CPUs.
 *
 * A compare and swap operation compares the contents of a memory location to a given value and,
 * only if they are the same, modifies the contents of that memory location to a given new value.
 *
 * The atomicity guarantees that the new value is calculated based on up-to-date information;
 * if the value had been updated by another thread in the meantime, the write would fail.
 *
 * CAS is implemented using compareAndSet function
 * compareAndSet method atomically checks if the current value equals the expected value.
 * If yes, the method updates the value to the new value and return true.
 * If not, the method leaves the current value unchanged and returns false.
 */
public class ThreadSafeCounter {
    private AtomicInteger count = new AtomicInteger(0);

    public  void increment() {
        count.incrementAndGet();
        //internal implementation of incrementAndGet
        /*int current = count.get();
        int newValue = current + 1;
        while( ! count.compareAndSet( current , newValue ) ) {
            current = count.get();
            newValue = current + 1;
        }*/
    }

    //
    public int getCount() {
        return count.get();
    }
}
