package com.ab.multithread.model;

/**
 * @author Arpit Bhardwaj
 */
public class Counter {
    private int count;

    //not thread-safe because ++ (increment operator) is not an atomic operation
    // and can be broken down into read, update and write operation.

    public int getCount(){
        return count++;
    }
}
