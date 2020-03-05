package com.ab.multithread.model;

import java.util.concurrent.Exchanger;

/**
 * @author Arpit Bhardwaj
 */
public class ConsumeString implements Runnable{
    private Exchanger<String> exchanger;
    private String str;

    public ConsumeString(Exchanger<String> exchanger) {
        this.exchanger = exchanger;

        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                if (i==2){
                    Thread.sleep(500);
                    continue;
                }

            // Exchange an empty string with a filled one
            str = exchanger.exchange(new String());
            System.out.println("Received String : " + str);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
