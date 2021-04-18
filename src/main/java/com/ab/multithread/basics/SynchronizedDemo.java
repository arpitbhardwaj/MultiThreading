package com.ab.multithread.basics;

import com.ab.multithread.model.Counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author Arpit Bhardwaj
 *
 *The synchronized keyword can be used on different levels:
 *
 * Instance methods
 * Instance methods are synchronized over the instance of the class owning the method.
 * Which means only one thread per instance of the class can execute this method.
 *
 * Static methods
 * These methods are synchronized on the Class object associated with the class and
 * since only one Class object exists per JVM per class,
 * only one thread can execute inside a static synchronized method per class, irrespective of the number of instances it has.
 *
 * Code blocks
 *
 *
 */
public class SynchronizedDemo {
    static class Counter{
        int count = 0;
        /*public int getCount(){
            count = count + 1;
            return count;
        }*/

        /*public synchronized int getCount(){
            count = count + 1;
            return count;
        }*/

        /*public static synchronized int getCount(){
            count = count + 1;
            return count;
        }*/

        /*public int getCount(){
            synchronized (this){
                count = count + 1;
            }
            return count;
        }*/

        public int getCount(){
            synchronized (Counter.class){
                count = count + 1;
            }
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Counter counter = new Counter();
        long startTime = System.currentTimeMillis();
        IntStream.range(1,1000)
                .forEach(count -> executorService.submit(counter::getCount));

        executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);

        executorService.shutdown();
        while(!executorService.isTerminated()){}

        System.out.println(counter.getCount());

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}
