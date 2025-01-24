package com.cts.controller;

import jakarta.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.Entity.User;
import com.cts.Repository.UserRepository;
import com.cts.dto.AuthenticationRequest;
import com.cts.dto.SignupRequestDto;
import com.cts.dto.UserDto;
import com.cts.services.authentication.AuthService;
import com.cts.services.userService.JwtUtil;
import com.cts.services.userService.UserDetailsServiceImpl;

import java.io.IOException;


@RestController
public class AuthenticationController {

    @Autowired
    private AuthService authService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    public static final String TOKEN_PREFIX="Bearer ";
    public static final String HEADER_STRING="Authorization";

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
    @PostMapping({"/login/UserLogin"})
    public void createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest,
            HttpServletResponse response
    ) throws IOException, JSONException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken (
                authenticationRequest.getUsername(), authenticationRequest.getPassword()
            ));
            System.out.println("Pass");
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
       
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        User user = userRepository.findFirstByEmail(authenticationRequest.getUsername());
        System.out.println("Hello pass");
        response.getWriter().write(new JSONObject()
                .put("userId", user.getId())
                .put("role", user.getRole())
                .toString()
        );
        System.out.println("Hello pass");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Access-Control-Allow-Headers", "Authorization," +
                " X-PINGOTHER, Orign, X-Requested-With, Content-Type, Accept, X-Custom-header");

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
    }



}
