package com.beyza.user.service.controller;

import com.beyza.user.service.ValueObject.ResponseTemplateValueObject;
import com.beyza.user.service.entity.Users;
import com.beyza.user.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public Users saveUser(@RequestBody Users user){
        log.info("Inside saveUser method of UserController ");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateValueObject getUserWithDepartment(@PathVariable("id") Long userId){
        log.info("Inside getUserWıthDepartment method of UserController ");
        return userService.getUserWithDepartment(userId);
    }
}
