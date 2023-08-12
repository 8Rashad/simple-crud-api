package com.example.jpa.mapper;

import com.example.jpa.dao.entity.UserEntity;
import com.example.jpa.model.UserRequest;
import com.example.jpa.model.UserResponse;

public class UserMapper {
    public static UserResponse mapEntityToResponse(UserEntity user){
        return UserResponse.builder()
                .id(user.getId())
                .age(user.getAge())
                .username(user.getUsername())
                .build();
    }

    public static UserEntity mapRequestToEntity(UserRequest user){
        return UserEntity.builder()
                .age(user.getAge())
                .username(user.getUsername())
                .build();
    }

}