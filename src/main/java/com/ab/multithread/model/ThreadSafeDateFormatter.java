package com.ab.multithread.model;

import java.text.SimpleDateFormat;

/**
 * @author Arpit Bhardwaj
 */
public class ThreadSafeDateFormatter {

    private static final ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            System.out.println("Create simple date format for thread " + Thread.currentThread().getName());
            return new SimpleDateFormat("dd/mm/yyyy");
        }

    };

    public static SimpleDateFormat getDateFormatter() {
        return dateFormatter.get();
    }
}
