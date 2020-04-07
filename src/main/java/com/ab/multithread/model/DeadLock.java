package com.ab.multithread.model;

public class DeadLock {
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
