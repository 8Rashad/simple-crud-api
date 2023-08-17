package com.example.jpa.service;

import org.springframework.stereotype.Service;

@Service
public class CardService {
    public boolean validateCard(String cardNumber){
        cardNumber = cardNumber.replaceAll("\\s", "");

        if (!cardNumber.matches("\\d+")){
            return false;
        }

        int sum = 0;
        boolean alternate = false;

        for(int i = cardNumber.length()-1; i >= 0; i--){
            int digit = Integer.parseInt(cardNumber.substring(i, i+1));
            if (alternate){
                digit *= 2;
                if (digit > 9){
                    digit -= 9;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        return sum % 10 == 0;
    }
}
