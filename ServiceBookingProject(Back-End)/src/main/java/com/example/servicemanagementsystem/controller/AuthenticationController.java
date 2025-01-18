package com.example.servicemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.example.servicemanagementsystem.dto.SignupRequestDto;
import com.example.servicemanagementsystem.dto.UserDto;
import com.example.servicemanagementsystem.services.authentication.AuthService;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @PostMapping("/client/sign-up")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> signupClient(@RequestBody SignupRequestDto signupRequestDto) {

        if (authService.presentByEmail(signupRequestDto.getEmail())) {
            return new ResponseEntity<>("Client already existe with this email", HttpStatus.NOT_ACCEPTABLE);
        }

        UserDto createUser = authService.signupClient(signupRequestDto);

        return new ResponseEntity<>(createUser, HttpStatus.OK);

    }


    @PostMapping("/company/sign-up")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> signupCompany(@RequestBody SignupRequestDto signupRequestDto) {

        if (authService.presentByEmail(signupRequestDto.getEmail())) {
            return new ResponseEntity<>("Comapny already existe with this email", HttpStatus.NOT_ACCEPTABLE);
        }

        UserDto createUser = authService.signupCompany(signupRequestDto);

        return new ResponseEntity<>(createUser, HttpStatus.OK);

    }



}
