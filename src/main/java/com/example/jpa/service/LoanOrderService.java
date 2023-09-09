package com.example.jpa.service;

import org.springframework.stereotype.Service;

@Service
public class LoanOrderService implements OrderService{
    @Override
    public void saveOrder() {
        System.out.println("I am from loan order");
    }
}
