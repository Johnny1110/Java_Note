package com.frizo.lab.asyncTask;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest5 {

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("doing first job.");
            return 15;
        }).thenAccept(val -> {
            System.out.println("doing second job.");
            System.out.println("get val from last task: " + val);
        }).thenAccept(val -> {
            System.out.println("doing third job.");
            System.out.println("get val from last task: " + val);
        });
    }
}
