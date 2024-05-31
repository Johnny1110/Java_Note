package com.frizo.lab.asyncTask;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {

    public static void main(String[] args) {
        CompletableFuture future1 = CompletableFuture.runAsync(() -> {
            System.out.println("do async task, and no return value.");
        });

        CompletableFuture future2 = CompletableFuture.supplyAsync(() -> {
            return "do async task, and return this string.";
        });

        System.out.println(future2.join());
    }
}
