package com.frizo.lab.asyncTask;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest8 {

    public static void main(String[] args) {
        CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("doing first job.");
            return 15;
        }).handle((val, err) -> {
            System.out.println("---------------------------------------");
            System.out.println("get val from last task: " + val);
            System.out.println("get error from last task: " + err);
            System.out.println("doing second job, throw a ex.");
            throw new RuntimeException("this is an error");
        }).handle((val, err) -> {
            System.out.println("---------------------------------------");
            System.out.println("get val from last task: " + val);
            System.out.println("get error from last task: " + err);
            System.out.println("doing third job.");
            return 100;
        });
        System.out.println("----------------------------------");
        System.out.println("final result:" + future.join());
    }
}
