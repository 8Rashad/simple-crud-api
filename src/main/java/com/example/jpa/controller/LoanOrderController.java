package com.example.jpa.controller;


import com.example.jpa.service.OrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/loan-orders")
public class LoanOrderController {
    private final OrderService orderService;
    public LoanOrderController(@Qualifier("postOrderService") OrderService orderService){
        this.orderService = orderService;
    }
    @PostMapping
    public void saveOrder(){
        orderService.saveOrder();
    }
}
