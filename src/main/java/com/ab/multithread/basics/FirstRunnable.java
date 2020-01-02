package com.ab.multithread.basics;

public class FirstRunnable {
    public static void main(String[] args) {
        /*Runnable runnable = new Runnable() {
            public void run() {
                System.out.println("I am running in " + Thread.currentThread().getName());
            }
        };*/
        Runnable runnable = ()-> {System.out.println("I am running in " + Thread.currentThread().getName());};
        Thread t = new Thread(runnable);
        t.setName("My Thread");
        //t.start();
        t.run();
    }
}
