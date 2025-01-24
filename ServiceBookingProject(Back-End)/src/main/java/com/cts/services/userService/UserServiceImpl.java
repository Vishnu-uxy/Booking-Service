package com.cts.services.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.Entity.User;
import com.cts.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
 
	@Autowired
	private UserRepository userRepository;
	@Override
	public User getUserByEmail(String email) {
		return userRepository.findFirstByEmail(email);
	}

}
