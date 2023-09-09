package com.example.jpa.service;

import org.springframework.stereotype.Service;

@Service
public class PostOrderService implements OrderService{
    @Override
    public void saveOrder() {
        System.out.println("I am from post service");
    }
}
