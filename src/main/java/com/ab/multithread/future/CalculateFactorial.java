package com.ab.multithread.future;

import java.util.concurrent.*;

/**
 * @author Arpit Bhardwaj
 */
public class CalculateFactorial {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(3);

    private static class FactorialCalculator implements Callable{
        private final int number;

        public FactorialCalculator(int number) {
            this.number = number;
        }

        @Override
        public Long call() throws Exception {
            return factorial(number);
        }

        private long factorial(int number) throws InterruptedException {
            if (number < 0){
                throw new IllegalArgumentException("Number must be greater than 0");
            }
            long result = 1;
            while (number > 0){
                Thread.sleep(1);
                result = result * number;
                number--;
            }
            return result;
        }
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FactorialCalculator task = new FactorialCalculator(20);
        System.out.println("Submitting Task...");
        Future<Long> future = executorService.submit(task);
        System.out.println("Task Submitted...");
        while (!future.isDone()){
            System.out.println("Task is not completed yet...");
            Thread.sleep(50);
        }
        System.out.println("Task is completed...");
        System.out.println("Result is : " + future.get());
        executorService.shutdown();
    }
}
