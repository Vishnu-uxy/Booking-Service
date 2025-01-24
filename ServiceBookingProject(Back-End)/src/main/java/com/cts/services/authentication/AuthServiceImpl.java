package com.example.servicemanagementsystem.services.authentication;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.servicemanagementsystem.Entity.User;
import com.example.servicemanagementsystem.Repository.UserRepository;
import com.example.servicemanagementsystem.dto.SignupRequestDto;
import com.example.servicemanagementsystem.dto.UserDto;
import com.example.servicemanagementsystem.enums.UserRole;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    public UserDto signupClient(SignupRequestDto signupRequestDto) {

        User user = new User();

        user.setName(signupRequestDto.getName());
        user.setLastname(signupRequestDto.getLastname());
        user.setEmail(signupRequestDto.getEmail());
        user.setPhone(signupRequestDto.getPhone());
        user.setPassword(signupRequestDto.getPassword());

        user.setRole(UserRole.CLIENT);
        return userRepository.save(user).getDto();

    }

    public Boolean presentByEmail(String email){


        return userRepository.findByEmail(email) != null;
    }

    public UserDto signupCompany(SignupRequestDto signupRequestDto) {

        User user = new User();

        user.setName(signupRequestDto.getName());
        user.setEmail(signupRequestDto.getEmail());
        user.setPhone(signupRequestDto.getPhone());
        user.setPassword(signupRequestDto.getPassword());

        user.setRole(UserRole.COMPANY);
        return userRepository.save(user).getDto();

    }

}
