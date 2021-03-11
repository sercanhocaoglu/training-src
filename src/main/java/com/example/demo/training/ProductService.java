package com.example.demo.training;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ProductService {

    public Product getProductDetail(String name) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("getProductDetail: " + name);
        return ProductBuilder.aProduct().id(1L).name(name).build();
    }

}
