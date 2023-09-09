package com.example.jpa.service;

import org.springframework.stereotype.Service;

@Service
public class A {
    public void test(){
        throw new RuntimeException("");
    }

    public static int sum(int a, int b){
        return a + b;
    }
}
