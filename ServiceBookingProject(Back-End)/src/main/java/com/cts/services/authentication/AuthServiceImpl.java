package com.cts.services.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.Entity.User;
import com.cts.Repository.UserRepository;
import com.cts.dto.SignupRequestDto;
import com.cts.dto.UserDto;
import com.cts.enums.UserRole;

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
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDto.getPassword()));

        user.setRole(UserRole.CLIENT);
        return userRepository.save(user).getDto();

    }

    public Boolean presentByEmail(String email){


        return userRepository.findFirstByEmail(email) != null;
    }

    public UserDto signupCompany(SignupRequestDto signupRequestDto) {

        User user = new User();

        user.setName(signupRequestDto.getName());
        user.setEmail(signupRequestDto.getEmail());
        user.setPhone(signupRequestDto.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDto.getPassword()));

        user.setRole(UserRole.COMPANY);
        return userRepository.save(user).getDto();

    }

}
