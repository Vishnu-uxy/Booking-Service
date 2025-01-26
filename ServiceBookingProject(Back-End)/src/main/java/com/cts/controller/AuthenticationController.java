package com.cts.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

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
    
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    @PostMapping("/client/sign-up")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> signupClient(@RequestBody SignupRequestDto signupRequestDto) {
        logger.info("Client sign-up request received for email: {}", signupRequestDto.getEmail());

        if (authService.presentByEmail(signupRequestDto.getEmail())) {
            logger.warn("Client already exists with email: {}", signupRequestDto.getEmail());
            return new ResponseEntity<>("Client already exists with this email", HttpStatus.NOT_ACCEPTABLE);
        }

        UserDto createUser = authService.signupClient(signupRequestDto);
        logger.info("Client sign-up successful for email: {}", signupRequestDto.getEmail());

        return new ResponseEntity<>(createUser, HttpStatus.OK);
    }

    @PostMapping("/company/sign-up")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> signupCompany(@RequestBody SignupRequestDto signupRequestDto) {
        logger.info("Company sign-up request received for email: {}", signupRequestDto.getEmail());

        if (authService.presentByEmail(signupRequestDto.getEmail())) {
            logger.warn("Company already exists with email: {}", signupRequestDto.getEmail());
            return new ResponseEntity<>("Company already exists with this email", HttpStatus.NOT_ACCEPTABLE);
        }

        UserDto createUser = authService.signupCompany(signupRequestDto);
        logger.info("Company sign-up successful for email: {}", signupRequestDto.getEmail());

        return new ResponseEntity<>(createUser, HttpStatus.OK);
    }

    @PostMapping("/login/UserLogin")
    public void createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest,
            HttpServletResponse response
    ) throws IOException, JSONException {
        logger.info("Authentication request received for username: {}", authenticationRequest.getUsername());

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()
            ));
            logger.info("Authentication successful for username: {}", authenticationRequest.getUsername());
        } catch (BadCredentialsException e) {
            logger.error("Authentication failed for username: {}", authenticationRequest.getUsername());
            throw new BadCredentialsException("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        User user = userRepository.findFirstByEmail(authenticationRequest.getUsername());

        logger.info("JWT generated for username: {}", authenticationRequest.getUsername());

        response.getWriter().write(new JSONObject()
                .put("userId", user.getId())
                .put("role", user.getRole())
                .toString()
        );

        logger.info("User information written to response for username: {}", authenticationRequest.getUsername());

        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
    }
    
    


}
