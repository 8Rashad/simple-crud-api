package com.example.jpa.mapper;

import com.example.jpa.dao.entity.UserEntity;
import com.example.jpa.model.request.UserRequest;
import com.example.jpa.model.response.PageableUserResponse;
import com.example.jpa.model.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserResponse mapEntityToResponse(UserEntity user){
        return UserResponse.builder()
                .id(user.getId())
                .age(user.getAge())
                .username(user.getUsername())
                .birthPlace(user.getBirthPlace())
                .build();
    }

    public static UserEntity mapRequestToEntity(UserRequest user){
        return UserEntity.builder()
                .age(user.getAge())
                .username(user.getUsername())
                .build();
    }
    public static PageableUserResponse mapToPageableResponse(Page<UserEntity> usersPage){
        return PageableUserResponse.builder()
                .users(usersPage.getContent().stream().map(UserMapper::mapEntityToResponse).collect(Collectors.toList()))
                .hasNextPage(usersPage.hasNext())
                .lastPageNumber(usersPage.getTotalPages())
                .totalElements(usersPage.getTotalElements())
                .build();

    }

}