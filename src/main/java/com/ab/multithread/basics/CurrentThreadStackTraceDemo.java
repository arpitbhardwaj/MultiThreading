package com.ab.multithread.basics;

/**
 * @author Arpit Bhardwaj
 */
public class CurrentThreadStackTraceDemo {
    public static void main(String[] args) {
        first();
    }

    private static void first() {
        second();
    }

    private static void second() {
        third();
    }

    private static void third() {

        System.out.println("Stack trace of current thread using dumpStack() method");
        Thread.currentThread().dumpStack();

        System.out.println("Printing stack trace using printStackTrace() method of Throwable ");
        new Throwable().printStackTrace();

        //If you want stack trace as StackTraceElement in program itself than
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (StackTraceElement traceElement:
             stackTraceElements) {
            System.out.println(traceElement.getLineNumber() + " : " +traceElement.getMethodName());
        }
    }
}
