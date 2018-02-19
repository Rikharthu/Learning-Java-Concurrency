package com.example.javaconcurrency.future;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
Future represents result of an asynchronous computation - a result that will
eventually appear in the Future after the processing is done.

Callable is an interface representing a task that returns a result

More info: http://www.baeldung.com/java-future
 */

public class Main {

    public static void main(String[] args) throws Exception {

        SquareCalculator calculator = new SquareCalculator();
        Future<Integer> resultFuture = calculator.calculate(2);

        // First way to get result of the future:
        int result = resultFuture.get(); // waits for result and retrieves it
        System.out.println("2 squared is: " + result);

        // Or just wait until it's done
        Future<Integer> secondResultFuture = calculator.calculate(3);
        while (!secondResultFuture.isDone()) {
            System.out.println("Calculating 3 squared...");
            Thread.sleep(100);
        }
        int secondResult = secondResultFuture.get();
        System.out.println("3 squared is: " + secondResult);


        Callable<String> myCallable = () -> "Hello World!";
        String greeting = myCallable.call();
        System.out.println(greeting);

        CompletableFuture<String> myCompletable = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello World!";
        });
        myCompletable.thenAccept(res -> {
            System.out.println("res = " + res);
        });

        System.out.println("Press enter to exit");
        new Scanner(System.in).nextLine();
    }
}
