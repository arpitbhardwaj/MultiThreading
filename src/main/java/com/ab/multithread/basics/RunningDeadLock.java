package com.ab.multithread.basics;

public class RunningDeadLock {

    private static class DeadLock {
        private Object key1 = new Object();
        private Object key2 = new Object();
        public void a(){
            synchronized (key1){
                System.out.println("[" + Thread.currentThread().getName()+ "] I am in a()");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b();
            }
        }
        public void b(){
            synchronized (key2){
                System.out.println("[" + Thread.currentThread().getName()+ "] I am in b()");
                c();
            }
        }
        public void c(){
            synchronized (key1){
                System.out.println("[" + Thread.currentThread().getName()+ "] I am in c()");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLock deadLock = new DeadLock();

        Runnable r1 = () -> deadLock.a();
        Runnable r2 = () -> deadLock.b();

        Thread t1 = new Thread(r1);
        t1.start();
        Thread t2 = new Thread(r2);
        t2.start();

        t1.join();
        t2.join();
    }
}
