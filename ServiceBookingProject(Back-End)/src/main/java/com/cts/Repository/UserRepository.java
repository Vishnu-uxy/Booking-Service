package com.example.servicemanagementsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.servicemanagementsystem.Entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User,Long>{

    User  findByEmail(String email);
}
