package com.frizo.lab.asyncTask;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest6 {

    public static void main(String[] args) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("doing first job.");
            return 15;
        }).thenApply(val -> {
            System.out.println("doing second job.");
            System.out.println("get val from last task: " + val);
            return val + 1;
        }).thenApply(val -> {
            System.out.println("doing third job.");
            System.out.println("get val from last task: " + val);
            return val + 1;
        });
        System.out.println("result: " + future.join());
    }
}
