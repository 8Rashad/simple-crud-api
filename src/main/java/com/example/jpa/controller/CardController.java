package com.example.jpa.controller;


import com.example.jpa.model.request.CardRequest;
import com.example.jpa.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/validate")
    public ResponseEntity<String> validateCard(@RequestBody CardRequest cardRequest){
        boolean isValid = cardService.validateCard(cardRequest.getNumber());
        if (isValid){
            return  ResponseEntity.ok("Valid credit card number");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid credit card number");
        }

    }

}
