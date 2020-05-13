package com.ab.multithread.problems;

import com.ab.multithread.model.ResourceLock;

/**
 * @author Arpit Bhardwaj
 */
public class PrintOddEven {
    public static void main(String[] args) {
        ResourceLock lock = new ResourceLock();

        //Even Thread
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    for (int i = 1; i < 20; i++) {
                        try {
                            while (lock.flag != 1) {
                                lock.wait();
                            }
                            if (i%2 == 0){
                                System.out.print(i + " ");
                            }
                            Thread.sleep(500);
                            lock.flag = 2;
                            lock.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        //Odd Thread
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    for (int i = 1; i < 20; i++) {
                        try {
                            while (lock.flag != 2) {
                                lock.wait();
                            }
                            if (i%2 != 0){
                                System.out.print(i + " ");
                            }
                            Thread.sleep(500);
                            lock.flag = 1;
                            lock.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
