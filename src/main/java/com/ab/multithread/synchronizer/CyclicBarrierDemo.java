package com.ab.multithread.synchronizer;

import com.ab.multithread.model.BarrierTask;

import java.util.concurrent.CyclicBarrier;

/**
 * @author Arpit Bhardwaj
 *
 * CyclicBarrier can be used to implement multiple threads waiting for each other.
 * you can reuse CyclicBarrier by calling reset() method which resets Barrier to its initial State.
 *
 * CyclicBarrier can be used to in case of the recurrent event e.g. concurrently calculating a solution of the big problem
 *
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        final CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("All parties are arrived at the barrier. Let move ahead");
            }
        });

        Thread t1 = new Thread(new BarrierTask(barrier), "Task 1");
        Thread t2 = new Thread(new BarrierTask(barrier), "Task 2");
        Thread t3 = new Thread(new BarrierTask(barrier), "Task 3");

        t1.start();
        t2.start();
        t3.start();
    }
}
