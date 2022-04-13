package com.numeratorx.usersmanagement.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/v1/")
public class UserController {

    @PostMapping("/register")
    public void register(){

    }

    @PostMapping("/login")
    public void login(){

    }

    @PutMapping("/update/profile")
    public void updateProfile(){

    }

    @GetMapping("/get/profile")
    public void getUserProfile(){

    }
}
