package com.cts.services.userService;

import com.cts.Entity.User;



public interface UserService {
    User getUserByEmail(String email);
}