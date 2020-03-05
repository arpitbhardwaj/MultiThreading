package com.ab.multithread.basics;

import com.ab.multithread.model.StopEnabledTask;

/**
 * @author Arpit Bhardwaj
 *
 * Thread in Java will stop once run() method finished
 * you can not restart a Thread which run() method has finished already , you will get an IllegalStateExceptio
 *
 * Manual Stop Mechanism shown below
 */
public class StopThread {
    public static void main(String[] args) throws InterruptedException {
        /*Thread t = new Thread(){
            boolean exit = false;

            public void setExit(boolean exit) {
                this.exit = exit;
            }

            @Override
            public void run() {
                while (!exit){
                    System.out.println("Running Thread");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };*/
        StopEnabledTask t = new StopEnabledTask();
        t.setName("Thread 1");
        t.start();
        Thread.sleep(1000);
        t.setExit(true);
    }
}
