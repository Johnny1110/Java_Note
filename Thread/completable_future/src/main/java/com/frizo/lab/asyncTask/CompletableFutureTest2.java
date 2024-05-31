package com.frizo.lab.asyncTask;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest2 {

    public static void main(String[] args) {
        CompletableFuture future1 = CompletableFuture.supplyAsync(() -> {
            return "future1: do async task, and return this string.";
        });
        CompletableFuture future2 = CompletableFuture.supplyAsync(() -> {
            return "return: do async task, and return this string.";
        });

        CompletableFuture<Object> anyFuture = CompletableFuture.anyOf(future1, future2);
        System.out.println(anyFuture.join());
    }
}
