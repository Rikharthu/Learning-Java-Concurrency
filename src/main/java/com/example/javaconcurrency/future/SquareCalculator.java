package com.example.javaconcurrency.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SquareCalculator {

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public Future<Integer> calculate(Integer input) {
        // Callable is an interface representing a task that returns a result
        // Submit a Callable and return a Future object
        return executor.submit(() -> {
            Thread.sleep(1000);
            return input * input;
        });
    }
}
