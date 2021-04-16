package com.ab.multithread.deadlock;

/**
 * @author Arpit Bhardwaj
 *
 * The statement “Thread.currentThread().join()”, will tell Main thread to wait for this thread(i.e. wait for itself) to die.
 * Thus Main thread wait for itself to die, which is nothing but a deadlock.
 */
public class DeadlockUsingMain {
    public static void main(String[] args) {
        try {
            System.out.println("Entering Deadlock!");
            Thread.currentThread().join();
            System.out.println("Never Execute!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
