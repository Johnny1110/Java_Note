package com.frizo.lab.asyncTask;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest4 {

    public static void main(String[] args) {
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            System.out.println("doing first job.");
            return 1;
        }).thenRun(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("doing second job.");
        }).thenRun(() -> {
            System.out.println("doing third job.");
        });

        System.out.println("Main Thread.");
        future.join();
    }
}
