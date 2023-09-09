package com.example.jpa.queue;

import com.example.jpa.model.enums.ObjectMapperSingleton;
import com.example.jpa.model.request.UserRequest;
import com.example.jpa.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserQueueListener {
    private final UserService userService;


    @RabbitListener(queues = "${rabbitmq.users.queue}")
    public void receiveMessage(String message) {
        try {
            userService.saveUser(ObjectMapperSingleton.INSTANCE.getObjectMapper().readValue(message, UserRequest.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
