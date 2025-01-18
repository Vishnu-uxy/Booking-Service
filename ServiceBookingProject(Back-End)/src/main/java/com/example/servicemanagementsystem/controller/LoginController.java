package com.example.servicemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicemanagementsystem.dto.LoginRequest;

import com.example.servicemanagementsystem.Entity.User;

import com.example.servicemanagementsystem.services.userService.UserServices;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserServices userService;
    
    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("UserLogin")
    public User UserCredential(@RequestBody LoginRequest loginRequest, HttpSession session ){
        User usercheck = userService.Userlogin(loginRequest);
            System.out.println(usercheck.getRole());
            return usercheck;//we need to change it  boolean later
    }
    
}
