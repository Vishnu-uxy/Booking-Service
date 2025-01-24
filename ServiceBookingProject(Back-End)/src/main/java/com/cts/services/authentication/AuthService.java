package com.cts.services.authentication;

import com.cts.dto.SignupRequestDto;
import com.cts.dto.UserDto;

public interface AuthService {

    UserDto signupClient(SignupRequestDto signupRequestDto);
    Boolean presentByEmail(String email);
    UserDto signupCompany(SignupRequestDto signupRequestDto);
    
} 