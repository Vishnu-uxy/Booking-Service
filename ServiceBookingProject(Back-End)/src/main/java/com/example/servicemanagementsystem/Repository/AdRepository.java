package com.example.servicemanagementsystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.servicemanagementsystem.Entity.Ad;

@Repository
public interface AdRepository extends JpaRepository<Ad,Long> {
    List<Ad> findAllByuserId(Long userId);


    List<Ad> findAllByServiceNameContaining(String name);

    List<Ad> findAllByCategory(String category);








}
