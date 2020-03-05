package com.ab.multithread.model;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Arpit Bhardwaj
 */
public class BuildString implements Runnable{
    private Exchanger<String> exchanger;
    private String str;

    public BuildString(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
        this.str = new String();

        new Thread(this).start();
    }

    @Override
    public void run() {
        char ch = 'A';
        try {
            for (int i = 0; i < 3; i++) {
                //build string
                for (int j = 0; j < 5; j++) {
                    str += ch++;
                }
                if (i==2){
                    str = exchanger.exchange(str,250, TimeUnit.MILLISECONDS);
                }
                System.out.println("Sending String : " + str);
                //exchange string
                str = exchanger.exchange(str);
            }
        }
        catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
