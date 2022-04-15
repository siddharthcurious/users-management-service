package com.numeratorx.usersmanagement.controllers;

import com.numeratorx.usersmanagement.models.User;
import com.numeratorx.usersmanagement.models.requests.RegistrationRequest;
import com.numeratorx.usersmanagement.models.responses.Response;
import com.numeratorx.usersmanagement.models.responses.StatusCode;
import com.numeratorx.usersmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/v1/")
public class UserController {

    @Autowired
    private UserService userService;

    private boolean isValidRequest(RegistrationRequest request){
        return request.getMobileNumber().isPresent() ||
                request.getUsername().isPresent() ||
                request.getEmailId().isPresent();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) {
        Map<String, Object> data = new HashMap<>();
        if(this.isValidRequest(request)){
            User user = userService.userExists(request);
            data.put("user", user);
            if(user == null) {
                user = userService.createUser(request);
                data.put("user", user);
                new ResponseEntity<>(new Response(
                        LocalDateTime.now(), "User registration successful", StatusCode.OK, data
                ), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(new Response(
                    LocalDateTime.now(), "User might already exists", StatusCode.NOT_OK, data
            ), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(new Response(
                LocalDateTime.now(), "Mobile number, email id or username missing", StatusCode.NOT_OK, new HashMap<>()
        ), HttpStatus.BAD_REQUEST);
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
