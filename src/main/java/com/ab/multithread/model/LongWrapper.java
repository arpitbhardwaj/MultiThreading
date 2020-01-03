package com.ab.multithread.model;

public class LongWrapper {
    private long l;
    private Object key = new Object();
    public LongWrapper(long l) {
        this.l = l;
    }

    public long getL() {
        return l;
    }

    public void incrementL() {
        synchronized (key){
            l = l + 1 ;
        }
    }
}
