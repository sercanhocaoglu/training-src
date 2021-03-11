package com.example.demo.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CompletableFutureClass {

    @Autowired
    private ProductService productService;

    public void executeAsync2() {
        CompletableFuture.runAsync(() -> {
            productService.getProductDetail("product-1");
        }).thenAccept(product -> {
            System.out.println("Got product detail from remote service " + 1);
        });
        CompletableFuture.runAsync(() -> {
            productService.getProductDetail("product-2");
        }).thenAccept(product -> {
            System.out.println("Got product detail from remote service " + 2);
        });
        CompletableFuture.runAsync(() -> {
            productService.getProductDetail("product-3");
        }).thenAccept(product -> {
            System.out.println("Got product detail from remote service " + 3);
        });
    }

    public void executeAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Product> future1
                = CompletableFuture.supplyAsync(() -> productService.getProductDetail("product-1"));
        CompletableFuture<Product> future2
                = CompletableFuture.supplyAsync(() -> productService.getProductDetail("product-2"));
        CompletableFuture<Product> future3
                = CompletableFuture.supplyAsync(() -> productService.getProductDetail("product-3"));
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(future1, future2, future3);
        objectCompletableFuture.get(); // block future 1,2,3 until they finished

    }

    public void executeSync() {
        productService.getProductDetail("product-1");
        productService.getProductDetail("product-2");
        productService.getProductDetail("product-3");
    }





    public void combine() throws ExecutionException, InterruptedException {
        System.out.println("Retrieving weight.");
        CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 65.0;
        });

        System.out.println("Retrieving height.");
        CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 177.8;
        });

        System.out.println("Calculating BMI.");
        CompletableFuture<Double> combinedFuture = weightInKgFuture
                .thenCombine(heightInCmFuture, (weightInKg, heightInCm) -> {
                    Double heightInMeter = heightInCm/100;
                    return weightInKg/(heightInMeter*heightInMeter);
                });

        System.out.println("Your BMI is - " + combinedFuture.get());
    }
}
