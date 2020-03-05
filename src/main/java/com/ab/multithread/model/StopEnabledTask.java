package com.ab.multithread.model;

/**
 * @author Arpit Bhardwaj
 */
public class StopEnabledTask extends Thread {
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
}
