package com.ab.multithread.model;

/**
 * @author Arpit Bhardwaj
 */
public class Task implements Runnable{

    private String command;

    public Task(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("Starting Task : " + threadName);
        System.out.println("Processing Command : " + command);
        processCommand();
        System.out.println("Ending Task : " + threadName);
    }

    private void processCommand() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
