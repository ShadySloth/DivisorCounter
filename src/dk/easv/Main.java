package dk.easv;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws Exception {

        // Fetches the start time of the method.
        Instant start = Instant.now();

        // Invokes the divisor counter
        //ExecutorService es = Executors.newFixedThreadPool(1);
        ExecutorService es = Executors.newFixedThreadPool(5);
        DivisorCounter task = new DivisorCounter(1, 100000);
        System.out.println("Looking for the best result...");

        Future<Result> future = es.submit((Callable<Result>) task);
        //es.invokeAll(Arrays.asList(task));

        // Fetches the end time of the method.

        // Find the highest result
        Result result = (Result) future.get();
        Instant end = Instant.now();
        System.out.println(result.getNumber() + " maxResult " + result.getDivisorCounter() + " divisors!");
        System.out.println("Duration: " + Duration.between(start, end).toMillis() + " ms");
        es.shutdown();
    }
}
