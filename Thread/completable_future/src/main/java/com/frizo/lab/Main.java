package com.frizo.lab;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture 功能介紹
 *
 * feat.1 執行多個 Async Task，取最快的一個
 * feat.1 Async Task callback
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> completableFuture =
                CompletableFuture.supplyAsync(() -> "this is msg from c1.")
                        .thenApply(val ->{
                            return val + "this is msg form c2.";
                        }).thenApply(val -> {
                            return val + "this is msg form c3.";
                        });

        completableFuture.join();

    }
}