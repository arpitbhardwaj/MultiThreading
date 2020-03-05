package com.ab.multithread.synchronizer;

import com.ab.multithread.model.BuildString;
import com.ab.multithread.model.ConsumeString;

import java.util.concurrent.Exchanger;

/**
 * @author Arpit Bhardwaj
 */
public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new BuildString(exchanger);
        new ConsumeString(exchanger);
    }
}
