package com.frizo.lab.asyncTask;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest3 {

    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "future1: do async task, and return this string.";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "return: do async task, and return this string.";
        });
        CompletableFuture<String> allFutures = CompletableFuture.allOf(future1, future2).thenApply(res -> {
            return future1.join() + future2.join();
        });
        System.out.println(allFutures.join());
    }
}
