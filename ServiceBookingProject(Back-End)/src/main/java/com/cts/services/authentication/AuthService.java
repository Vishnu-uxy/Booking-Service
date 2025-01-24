package com.example.servicemanagementsystem.services.authentication;

import com.example.servicemanagementsystem.dto.SignupRequestDto;
import com.example.servicemanagementsystem.dto.UserDto;

public interface AuthService {

    UserDto signupClient(SignupRequestDto signupRequestDto);
    Boolean presentByEmail(String email);
    UserDto signupCompany(SignupRequestDto signupRequestDto);
    
} 