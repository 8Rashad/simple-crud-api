package com.example.jpa.controller;

import com.example.jpa.model.criteria.PageCriteria;
import com.example.jpa.model.criteria.UserCriteria;
import com.example.jpa.model.request.UserRequest;
import com.example.jpa.model.response.PageableUserResponse;
import com.example.jpa.model.response.UserResponse;
import com.example.jpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

//    @GetMapping
//    public List<UserResponse> getUsers(){
//        return userService.getUsers();
//    }


    @PostMapping
    @ResponseStatus(CREATED)
    public void saveUser(@RequestBody UserRequest user){

        userService.saveUser(user);
    }

    @PatchMapping("/{id}/birth-place")
    @ResponseStatus(NO_CONTENT)
    public void setBirthPlace(@PathVariable Long id, @RequestParam String birthPlace){
        userService.setBirthPlace(id, birthPlace);
    }

    @GetMapping
    public PageableUserResponse getUsers(PageCriteria pageCriteria, UserCriteria userCriteria){
        return userService.getUsers(pageCriteria, userCriteria);
    }
}
