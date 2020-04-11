package com.ab.multithread.basics;

/**
 * @author Arpit Bhardwaj
 *
 * IllegalMonitorStateException which wait() and notify() methods throw when they get called from non-synchronized context
 * so obj.wait() and if this call throws an exception it means thread in Java is not holding the lock, otherwise thread holds the lock.
 *
 * thread static method called holdsLock(Object obj) which returns true or false based on
 * whether threads holds a lock on the object passed.
 *
 */
public class CheckHoldLock implements Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(new CheckHoldLock());
        t.start();
    }

    @Override
    public void run() {
        System.out.println("Holds Lock : " + Thread.holdsLock(this));
        synchronized (this){
            System.out.println("Holds Lock : " + Thread.holdsLock(this));
        }
    }
}
