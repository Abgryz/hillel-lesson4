package ithillel.lesson13;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleCalculator {
    public static int squareSum(int first, int second){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> first*first, executor);
            return CompletableFuture.supplyAsync(() -> second*second, executor)
                    .thenCombine(future, Integer::sum)
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
