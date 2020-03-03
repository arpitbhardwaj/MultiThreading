package com.ab.multithread.problems;

import com.ab.multithread.model.ResourceLock;

/**
 * @author Arpit Bhardwaj
 */
public class PrintAlternate {
    public static void main(String[] args) {
        ResourceLock lock = new ResourceLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    for (int i = 0; i < 10; i++) {
                        try {
                            while (lock.flag != 1) {
                                lock.wait();
                            }
                            System.out.print("A ");
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

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    for (int i = 0; i < 10; i++) {
                        try {
                            while (lock.flag != 2) {
                                lock.wait();
                            }
                            System.out.print("B ");
                            Thread.sleep(500);
                            lock.flag = 3;
                            lock.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    for (int i = 0; i < 10; i++) {
                        try {
                            while (lock.flag != 3) {
                                lock.wait();
                            }
                            System.out.print("C ");
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
        t3.start();
    }
}
