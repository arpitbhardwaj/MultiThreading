package com.ab.multithread.threadsafecode;

import com.ab.multithread.model.ThreadSafeDateFormatter;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author Arpit Bhardwaj
 *
 * ThreadLocal in Java is another way to achieve thread-safety apart from writing immutable classes
 *
 * ThreadLocal in Java is a different way to achieve thread-safety, it doesn't address synchronization requirement,
 * instead it eliminates sharing by providing explicitly copy of Object to each thread.
 *
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    System.out.println(Thread.currentThread().getName() + " Formatted Date: " + ThreadLocalDemo.threadSafeDateFormat(new Date()));
                }
            }
        }, "Thread 1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    System.out.println(Thread.currentThread().getName() + " Formatted Date: " + ThreadLocalDemo.threadSafeDateFormat(new Date()));
                }
            }
        },"Thread 2");

        t1.start();
        t2.start();
    }

    private static String threadSafeDateFormat(Date date) {
        DateFormat dateFormat = ThreadSafeDateFormatter.getDateFormatter();
        return dateFormat.format(date);
    }
}
