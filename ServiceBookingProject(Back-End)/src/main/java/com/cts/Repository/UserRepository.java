package com.cts.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.Entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User,Long>{

	User findFirstByEmail(String email);
}
