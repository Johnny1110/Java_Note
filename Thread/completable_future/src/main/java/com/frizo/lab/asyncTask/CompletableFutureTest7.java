package com.frizo.lab.asyncTask;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest7 {

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("doing first job.");
            return 15;
        }).whenComplete((val, err) -> {
            System.out.println("---------------------------------------");
            System.out.println("get val from last task: " + val);
            System.out.println("get error from last task: " + err);
            System.out.println("doing second job, throw a ex.");
            throw new RuntimeException("this is an error");
        }).whenComplete((val, err) -> {
            System.out.println("---------------------------------------");
            System.out.println("get val from last task: " + val);
            System.out.println("get error from last task: " + err);
            System.out.println("doing third job.");
        });
    }
}
